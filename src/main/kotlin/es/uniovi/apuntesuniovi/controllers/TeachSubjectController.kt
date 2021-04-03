package es.uniovi.apuntesuniovi.controllers

import es.uniovi.apuntesuniovi.dtos.entities.TeachSubjectDto
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
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
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
}