package es.uniovi.apuntesuniovi.controllers

import es.uniovi.apuntesuniovi.controllers.commands.teachSubjects.CreateTeachSubject
import es.uniovi.apuntesuniovi.controllers.commands.teachSubjects.FindAllTeachSubjects
import es.uniovi.apuntesuniovi.models.TeachSubject
import es.uniovi.apuntesuniovi.services.BaseService
import es.uniovi.apuntesuniovi.services.TeachSubjectService
import es.uniovi.apuntesuniovi.services.dtos.entities.TeachSubjectDto
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
  private val TeachSubjectService: TeachSubjectService
) : BaseController<TeachSubject, TeachSubjectDto>(TeachSubjectService) {

  override fun create(
    baseService: BaseService<TeachSubject, TeachSubjectDto>,
    json: String
  ): TeachSubjectDto {
    return CreateTeachSubject(TeachSubjectService, json).execute()
  }

  override fun findAll(
    baseService: BaseService<TeachSubject, TeachSubjectDto>,
    pageable: Pageable
  ): Page<TeachSubjectDto> {
    return FindAllTeachSubjects(TeachSubjectService, pageable).execute()
  }
}