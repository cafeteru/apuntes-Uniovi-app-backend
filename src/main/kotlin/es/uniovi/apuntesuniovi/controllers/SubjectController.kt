package es.uniovi.apuntesuniovi.controllers

import es.uniovi.apuntesuniovi.controllers.commands.subjects.CreateSubject
import es.uniovi.apuntesuniovi.controllers.commands.subjects.FindAllSubjects
import es.uniovi.apuntesuniovi.models.Subject
import es.uniovi.apuntesuniovi.services.BaseService
import es.uniovi.apuntesuniovi.services.SubjectService
import es.uniovi.apuntesuniovi.services.dtos.entities.SubjectDto
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
) : BaseController<Subject, SubjectDto>(subjectService) {

    override fun create(baseService: BaseService<Subject, SubjectDto>, json: String): List<SubjectDto> {
        return CreateSubject(subjectService, json).execute()
    }

    override fun findAll(baseService: BaseService<Subject, SubjectDto>, pageable: Pageable): Page<SubjectDto> {
        return FindAllSubjects(subjectService, pageable).execute()
    }
}