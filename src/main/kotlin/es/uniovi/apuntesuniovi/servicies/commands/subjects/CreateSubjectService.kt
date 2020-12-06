package es.uniovi.apuntesuniovi.servicies.commands.subjects

import es.uniovi.apuntesuniovi.entities.Subject
import es.uniovi.apuntesuniovi.infrastructure.AbstractCommand
import es.uniovi.apuntesuniovi.repositories.SubjectRepository

/**
 * Create a subject in service layer
 */
class CreateSubjectService(
    private val subjectRepository: SubjectRepository,
    private val subject: Subject
) : AbstractCommand<List<Subject>>() {
    override fun execute(): List<Subject> {
        logService.info("execute() - start")
        val result = subjectRepository.save(subject)
        logService.info("execute() - end")
        return listOf(result)
    }
}