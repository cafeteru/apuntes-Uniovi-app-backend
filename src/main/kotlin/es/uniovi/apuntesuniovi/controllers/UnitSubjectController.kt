package es.uniovi.apuntesuniovi.controllers

import es.uniovi.apuntesuniovi.models.UnitSubject
import es.uniovi.apuntesuniovi.services.BaseService
import es.uniovi.apuntesuniovi.services.UnitSubjectService
import es.uniovi.apuntesuniovi.dtos.entities.UnitSubjectDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Define UnitSubject endpoints
 */
@RestController
@RequestMapping("/unitSubjects")
class UnitSubjectController @Autowired constructor(
  private val unitSubjectService: UnitSubjectService
) : BaseController<UnitSubject, UnitSubjectDto>(unitSubjectService) {

  override fun create(
    baseService: BaseService<UnitSubject, UnitSubjectDto>,
    dto: UnitSubjectDto
  ): UnitSubjectDto {
    return unitSubjectService.create(dto)
  }

  override fun findAll(
    baseService: BaseService<UnitSubject, UnitSubjectDto>,
    pageable: Pageable
  ): Page<UnitSubjectDto> {
    return unitSubjectService.findAll(pageable)
  }
}