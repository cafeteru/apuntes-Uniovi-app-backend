package es.uniovi.apuntesuniovi.controllers

import es.uniovi.apuntesuniovi.dtos.entities.LearnSubjectDto
import es.uniovi.apuntesuniovi.dtos.entities.UserDto
import es.uniovi.apuntesuniovi.infrastructure.log.LogService
import es.uniovi.apuntesuniovi.services.LearnSubjectService
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

@RestController
@RequestMapping("/learnSubjects")
@Api(tags = ["Learn Subjects"])
class LearnSubjectController @Autowired constructor(
    private val learnSubjectService: LearnSubjectService
) {
    private val logService = LogService(this.javaClass)

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(value = ["/create/{id}"], consumes = [MediaType.APPLICATION_JSON_VALUE])
    @ApiOperation(value = "Create a list of learn subject")
    fun create(
        @ApiParam(name = "id", value = "Subject´s id")
        @PathVariable id: Long,
        @ApiParam(name = "list", value = "List of Learn Subjects to create")
        @Valid @RequestBody list: List<LearnSubjectDto>
    ): ResponseEntity<List<LearnSubjectDto>> {
        logService.info("create(id: Long, list: List<LearnSubjectDto>) - start")
        val result = learnSubjectService.create(id, list)
        logService.info("create(id: Long, list: List<LearnSubjectDto>) - end")
        return ResponseEntity(result, HttpStatus.OK)
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/subject/{id}")
    @ApiOperation("Return a list of learnSubjects by subject´s id")
    fun findStudentsBySubjectId(
        @ApiParam(name = "id", value = "Subject´s id")
        @PathVariable id: Long,
        @ApiParam(name = "pageable", value = "Pageable")
        pageable: Pageable,
    ): ResponseEntity<Page<UserDto>> {
        logService.info("findStudentsBySubjectId(id: ${id}) - start")
        val page = learnSubjectService.findStudentsBySubjectId(id, pageable)
        var code = HttpStatus.OK
        if (page.isEmpty) {
            code = HttpStatus.NO_CONTENT
        }
        logService.info("findStudentsBySubjectId(id: ${id}) - end")
        return ResponseEntity(page, code)
    }
}