package es.uniovi.apuntesuniovi.controllers

import es.uniovi.apuntesuniovi.models.Subject
import es.uniovi.apuntesuniovi.services.BaseService
import es.uniovi.apuntesuniovi.services.SubjectService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Define subject endpoints
 */
@RestController
@RequestMapping("/subjects")
class SubjectController @Autowired constructor(
  private val subjectService: SubjectService
) : BaseController<Subject>(subjectService) {

  override fun create(
    baseService: BaseService<Subject>,
    entity: Subject
  ): Subject {
    return subjectService.create(entity)
  }

  override fun findAll(
    baseService: BaseService<Subject>,
    pageable: Pageable
  ): Page<Subject> {
    return subjectService.findAll(pageable)
  }
}