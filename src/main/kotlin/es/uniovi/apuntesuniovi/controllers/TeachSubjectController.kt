package es.uniovi.apuntesuniovi.controllers

import es.uniovi.apuntesuniovi.models.TeachSubject
import es.uniovi.apuntesuniovi.services.BaseService
import es.uniovi.apuntesuniovi.services.TeachSubjectService
import es.uniovi.apuntesuniovi.dtos.entities.TeachSubjectDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Define TeachSubject endpoints
 */
@RestController
@RequestMapping("/teachSubjects")
class TeachSubjectController @Autowired constructor(
  private val teachSubjectService: TeachSubjectService
) : BaseController<TeachSubject, TeachSubjectDto>(teachSubjectService) {

  override fun create(
    baseService: BaseService<TeachSubject, TeachSubjectDto>,
    dto: TeachSubjectDto
  ): TeachSubjectDto {
    return teachSubjectService.create(dto)
  }

  override fun findAll(
    baseService: BaseService<TeachSubject, TeachSubjectDto>,
    pageable: Pageable
  ): Page<TeachSubjectDto> {
    return teachSubjectService.findAll(pageable)
  }
}