package es.uniovi.apuntesuniovi.controllers

import es.uniovi.apuntesuniovi.models.TeachSubject
import es.uniovi.apuntesuniovi.services.BaseService
import es.uniovi.apuntesuniovi.services.TeachSubjectService
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
) : BaseController<TeachSubject>(teachSubjectService) {

  override fun create(
    baseService: BaseService<TeachSubject>,
    entity: TeachSubject
  ): TeachSubject {
    return teachSubjectService.create(entity)
  }

  override fun findAll(
    baseService: BaseService<TeachSubject>,
    pageable: Pageable
  ): Page<TeachSubject> {
    return teachSubjectService.findAll(pageable)
  }
}