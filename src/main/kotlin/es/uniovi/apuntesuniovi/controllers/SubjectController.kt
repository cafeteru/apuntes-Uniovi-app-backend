package es.uniovi.apuntesuniovi.controllers

import es.uniovi.apuntesuniovi.dtos.entities.SubjectDto
import es.uniovi.apuntesuniovi.infrastructure.log.LogService
import es.uniovi.apuntesuniovi.services.SubjectService
import es.uniovi.apuntesuniovi.statistics.SubjectStatistics
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

/**
 * Define subject endpoints
 */
@RestController
@RequestMapping("/subjects")
@Api(tags = ["Subjects"])
class SubjectController @Autowired constructor(
    private val subjectService: SubjectService
) {
    private val logService = LogService(this.javaClass)

    /**
     * Create a new subject
     *
     * @param subjectDto Subject to create
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(value = ["/create"], consumes = [MediaType.APPLICATION_JSON_VALUE])
    @ApiOperation(value = "Create a new subject")
    fun create(
        @ApiParam(name = "subjectDto", value = "Subject to create")
        @Valid @RequestBody subjectDto: SubjectDto
    ): ResponseEntity<SubjectDto> {
        logService.info("save(subjectDto: SubjectDto) - start")
        val result = subjectService.create(subjectDto)
        logService.info("save(subjectDto: SubjectDto) - end")
        return ResponseEntity(result, HttpStatus.OK)
    }

    /**
     * Returns all registered subjects
     *
     * @param subjectDto Subject to apply filters
     * @param pageable Pageable
     */
    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_TEACHER')")
    @PostMapping("")
    @ApiOperation("Returns all registered subjects")
    fun findAll(
        @ApiParam(name = "pageable", value = "Pageable") pageable: Pageable,
        @ApiParam(name = "subjectDto", value = "Subject to apply filters")
        @RequestBody(required = false) subjectDto: SubjectDto?
    ): ResponseEntity<Page<SubjectDto>> {
        logService.info("findAll() - start")
        val page = subjectService.findAll(pageable, subjectDto)
        var code = HttpStatus.OK
        if (page.isEmpty) {
            code = HttpStatus.NO_CONTENT
        }
        logService.info("findAll() - end")
        return ResponseEntity(page, code)
    }

    /**
     * Return a subject by id
     *
     * @param id Subject´s id
     */
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    @ApiOperation("Return a subject by id")
    fun findById(
        @ApiParam(name = "id", value = "Subject´s id")
        @PathVariable id: Long
    ): ResponseEntity<SubjectDto> {
        logService.info("findById(id: ${id}) - start")
        val result = subjectService.findById(id)
        logService.info("findById(id: ${id}) - end")
        return ResponseEntity(result, HttpStatus.OK)
    }

    /**
     * Create a new subject
     *
     * @param id Subject´s id
     * @param subjectDto Subject to update
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping(value = ["/{id}"], consumes = [MediaType.APPLICATION_JSON_VALUE])
    @ApiOperation(value = "Update a subject")
    fun update(
        @ApiParam(name = "id", value = "Subject´s id") @PathVariable id: Long,
        @ApiParam(name = "subjectDto", value = "Subject to update")
        @Valid @RequestBody subjectDto: SubjectDto
    ): ResponseEntity<SubjectDto> {
        logService.info("update(id: ${id}, subjectDto: SubjectDto) - start")
        val result = subjectService.update(id, subjectDto)
        logService.info("update(id: ${id}, subjectDto: SubjectDto) - end")
        return ResponseEntity(result, HttpStatus.OK)
    }

    /**
     * Change the value active of a subject
     *
     * @param id Subject´s id
     * @param active New value to subject´s active
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PatchMapping("/disable/{id}/{active}")
    @ApiOperation("Change the value active of a subject")
    fun disable(
        @ApiParam(name = "id", value = "Subject´s id") @PathVariable id: Long,
        @ApiParam(name = "active", value = "New value to subject´s active")
        @PathVariable active: Boolean
    ): ResponseEntity<SubjectDto> {
        logService.info("disable(id: ${id}, active: ${active}) - start")
        val subjectDto = subjectService.disable(id, active)
        logService.info("disable(id: ${id}, active: ${active}) - end")
        return ResponseEntity(subjectDto, HttpStatus.OK)
    }

    /**
     * Delete a subject
     *
     * @param id Subject´s id
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    @ApiOperation("Delete a subject")
    fun delete(
        @ApiParam(name = "id", value = "Subject´s id") @PathVariable id: Long
    ): ResponseEntity<Boolean> {
        logService.info("delete(id: ${id}) - start")
        val result = subjectService.delete(id)
        logService.info("delete(id: ${id}) - end")
        return ResponseEntity(result, HttpStatus.OK)
    }

    /**
     * Return subject statistics
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/statistics")
    @ApiOperation("Return subject statistics")
    fun statistics(): ResponseEntity<SubjectStatistics> {
        logService.info("statistics() - start")
        val userStatistics = subjectService.statistics()
        logService.info("statistics() - end")
        return ResponseEntity(userStatistics, HttpStatus.OK)
    }
}