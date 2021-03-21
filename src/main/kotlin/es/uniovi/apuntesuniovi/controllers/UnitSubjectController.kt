package es.uniovi.apuntesuniovi.controllers

import es.uniovi.apuntesuniovi.models.UnitSubject
import es.uniovi.apuntesuniovi.services.BaseService
import es.uniovi.apuntesuniovi.services.UnitSubjectService
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
) : BaseController<UnitSubject>(unitSubjectService) {

  override fun create(
    baseService: BaseService<UnitSubject>,
    entity: UnitSubject
  ): UnitSubject {
    return unitSubjectService.create(entity)
  }

  override fun findAll(
    baseService: BaseService<UnitSubject>,
    pageable: Pageable
  ): Page<UnitSubject> {
    return unitSubjectService.findAll(pageable)
  }
}