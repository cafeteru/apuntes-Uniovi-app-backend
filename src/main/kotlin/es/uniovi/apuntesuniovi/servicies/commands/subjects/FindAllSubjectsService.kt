package es.uniovi.apuntesuniovi.servicies.commands.subjects

import es.uniovi.apuntesuniovi.entities.Subject
import es.uniovi.apuntesuniovi.infrastructure.Command
import es.uniovi.apuntesuniovi.infrastructure.log.LogService
import es.uniovi.apuntesuniovi.repositories.SubjectRepository

/**
 * Return all subjects in service layer
 */
class FindAllSubjectsService(
    private val subjectRepository: SubjectRepository
) : Command<List<Subject>> {
    private val logService = LogService(this.javaClass)

    override fun execute(): List<Subject> {
        logService.info("execute() - start")
        val list = subjectRepository.findAll()
        logService.info("execute() - end")
        return list
    }
}