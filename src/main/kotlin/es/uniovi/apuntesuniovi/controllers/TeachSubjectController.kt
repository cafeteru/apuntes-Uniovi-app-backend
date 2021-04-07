package es.uniovi.apuntesuniovi.controllers

import es.uniovi.apuntesuniovi.dtos.entities.SubjectDto
import es.uniovi.apuntesuniovi.dtos.entities.TeachSubjectDto
import es.uniovi.apuntesuniovi.dtos.entities.UserDto
import es.uniovi.apuntesuniovi.infrastructure.log.LogService
import es.uniovi.apuntesuniovi.services.TeachSubjectService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/teachSubjects")
@Api(tags = ["Teach Subjects"])
class TeachSubjectController @Autowired constructor(
    private val teachSubjectService: TeachSubjectService
) {
    private val logService = LogService(this.javaClass)

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(value = ["/create"], consumes = [MediaType.APPLICATION_JSON_VALUE])
    @ApiOperation(value = "Create a new teach subject")
    fun create(
        @ApiParam(name = "list", value = "List of Teach Subjects to create")
        @Valid @RequestBody list: List<TeachSubjectDto>
    ): ResponseEntity<List<TeachSubjectDto>> {
        logService.info("save(list: List<TeachSubjectDto>) - start")
        val result = teachSubjectService.create(list)
        logService.info("save(list: List<TeachSubjectDto>) - end")
        return ResponseEntity(result, HttpStatus.OK)
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/subject/{id}")
    @ApiOperation("Return a list of teachSubjects by subject´s id")
    fun findTeachersBySubjectId(
        @ApiParam(name = "id", value = "Subject´s id")
        @PathVariable id: Long
    ): ResponseEntity<List<UserDto>> {
        logService.info("findById(id: ${id}) - start")
        val result = teachSubjectService.findTeachersBySubjectId(id)
        logService.info("findById(id: ${id}) - end")
        return ResponseEntity(result, HttpStatus.OK)
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping(value = ["/{id}"], consumes = [MediaType.APPLICATION_JSON_VALUE])
    @ApiOperation(value = "Update a teach Subject")
    fun update(
        @ApiParam(name = "id", value = "Teach Subject´s id") @PathVariable id: Long,
        @ApiParam(name = "list", value = "List of Teach Subjects to create")
        @Valid @RequestBody list: List<TeachSubjectDto>
    ): ResponseEntity<List<TeachSubjectDto>> {
        logService.info("update(id: ${id}, list: List<TeachSubjectDto>) - start")
        val result = teachSubjectService.update(id, list)
        logService.info("update(id: ${id}, list: List<TeachSubjectDto>) - end")
        return ResponseEntity(result, HttpStatus.OK)
    }
}