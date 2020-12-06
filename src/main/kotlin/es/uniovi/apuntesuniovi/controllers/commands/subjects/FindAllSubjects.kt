package es.uniovi.apuntesuniovi.controllers.commands.subjects

import es.uniovi.apuntesuniovi.infrastructure.AbstractCommand
import es.uniovi.apuntesuniovi.servicies.SubjectService
import es.uniovi.apuntesuniovi.servicies.dtos.entities.SubjectDto

/**
 * Return all subjects in controller layer
 */
class FindAllSubjects(
    private val subjectService: SubjectService
) : AbstractCommand<List<SubjectDto>>() {
    override fun execute(): List<SubjectDto> {
        logService.info("execute() - start")
        val result = subjectService.findAll()
        logService.info("execute() - end")
        return result
    }
}