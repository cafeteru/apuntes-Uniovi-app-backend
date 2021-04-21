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
import org.springframework.http.MediaType
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
    @PostMapping(value = ["/create/{id}"], consumes = [MediaType.APPLICATION_JSON_VALUE])
    @ApiOperation(value = "Create a list of teach subject")
    fun create(
        @ApiParam(name = "id", value = "Subject´s id")
        @PathVariable id: Long,
        @ApiParam(name = "list", value = "List of Teach Subjects to create")
        @Valid @RequestBody list: List<TeachSubjectDto>
    ): List<TeachSubjectDto> {
        logService.info("save(list: List<TeachSubjectDto>) - start")
        val result = teachSubjectService.create(id, list)
        logService.info("save(list: List<TeachSubjectDto>) - end")
        return result
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/subject/{id}")
    @ApiOperation("Return a list of teachSubjects by subject´s id")
    fun findTeachersBySubjectId(
        @ApiParam(name = "id", value = "Subject´s id")
        @PathVariable id: Long
    ): List<UserDto> {
        logService.info("findById(id: ${id}) - start")
        val result = teachSubjectService.findTeachersBySubjectId(id)
        logService.info("findById(id: ${id}) - end")
        return result
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_TEACHER')")
    @GetMapping("/teacher/{id}")
    @ApiOperation("Return a list of subjects by teacher´s id")
    fun findSubjectsByTeacherId(
        @ApiParam(name = "id", value = "Teacher´s id")
        @PathVariable id: Long
    ): List<SubjectDto> {
        logService.info("findById(id: ${id}) - start")
        val result = teachSubjectService.findSubjectsByTeacherId(id)
        logService.info("findById(id: ${id}) - end")
        return result
    }
}