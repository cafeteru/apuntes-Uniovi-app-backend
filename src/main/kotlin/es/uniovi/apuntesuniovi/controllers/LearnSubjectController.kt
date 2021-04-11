package es.uniovi.apuntesuniovi.controllers

import es.uniovi.apuntesuniovi.dtos.entities.LearnSubjectDto
import es.uniovi.apuntesuniovi.dtos.entities.UserDto
import es.uniovi.apuntesuniovi.infrastructure.log.LogService
import es.uniovi.apuntesuniovi.services.LearnSubjectService
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
@RequestMapping("/LearnSubjects")
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
        logService.info("save(list: List<LearnSubjectDto>) - start")
        val result = learnSubjectService.create(id, list)
        logService.info("save(list: List<LearnSubjectDto>) - end")
        return ResponseEntity(result, HttpStatus.OK)
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/subject/{id}")
    @ApiOperation("Return a list of learnSubjects by subject´s id")
    fun findStudentsBySubjectId(
        @ApiParam(name = "id", value = "Subject´s id")
        @PathVariable id: Long
    ): ResponseEntity<List<UserDto>> {
        logService.info("findById(id: ${id}) - start")
        val result = learnSubjectService.findStudentsBySubjectId(id)
        logService.info("findById(id: ${id}) - end")
        return ResponseEntity(result, HttpStatus.OK)
    }
}