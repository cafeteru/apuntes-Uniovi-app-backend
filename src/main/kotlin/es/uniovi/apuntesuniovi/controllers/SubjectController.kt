package es.uniovi.apuntesuniovi.controllers

import es.uniovi.apuntesuniovi.controllers.commands.subjects.CreateSubject
import es.uniovi.apuntesuniovi.controllers.commands.subjects.FindAllSubjects
import es.uniovi.apuntesuniovi.infrastructure.AbstractCommand
import es.uniovi.apuntesuniovi.services.BaseService
import es.uniovi.apuntesuniovi.services.SubjectService
import es.uniovi.apuntesuniovi.services.dtos.entities.SubjectDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Define subject endpoints
 */
@RestController
@RequestMapping("/subjects")
class SubjectController @Autowired constructor(
    private val subjectService: SubjectService
) : BaseController<SubjectDto>(subjectService) {

    override fun getCreateCommand(
        baseService: BaseService<SubjectDto>,
        json: String
    ): AbstractCommand<List<SubjectDto>> {
        return CreateSubject(subjectService, json)
    }

    override fun getFindAllCommand(baseService: BaseService<SubjectDto>): AbstractCommand<List<SubjectDto>> {
        return FindAllSubjects(subjectService)
    }
}