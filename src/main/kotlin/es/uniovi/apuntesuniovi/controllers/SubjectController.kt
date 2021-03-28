package es.uniovi.apuntesuniovi.controllers

import es.uniovi.apuntesuniovi.dtos.entities.SubjectDto
import es.uniovi.apuntesuniovi.infrastructure.log.LogService
import es.uniovi.apuntesuniovi.services.SubjectService
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
}