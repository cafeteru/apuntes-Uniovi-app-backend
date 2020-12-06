package es.uniovi.apuntesuniovi.services.commands.subjects

import es.uniovi.apuntesuniovi.models.Subject
import es.uniovi.apuntesuniovi.infrastructure.AbstractCommand
import es.uniovi.apuntesuniovi.repositories.SubjectRepository

/**
 * Return all subjects in service layer
 */
class FindAllSubjectsService(
    private val subjectRepository: SubjectRepository
) : AbstractCommand<List<Subject>>() {
    override fun execute(): List<Subject> {
        logService.info("execute() - start")
        val list = subjectRepository.findAll()
        logService.info("execute() - end")
        return list
    }
}