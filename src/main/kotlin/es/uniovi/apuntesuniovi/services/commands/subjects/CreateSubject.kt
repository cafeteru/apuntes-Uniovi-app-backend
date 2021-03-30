package es.uniovi.apuntesuniovi.services.commands.subjects

import es.uniovi.apuntesuniovi.infrastructure.AbstractCommand
import es.uniovi.apuntesuniovi.models.Subject
import es.uniovi.apuntesuniovi.repositories.SubjectRepository

/**
 * Create a subject in service layer
 */
class CreateSubject(
    private val subjectRepository: SubjectRepository,
    private val subject: Subject
) : AbstractCommand<Subject>() {

    override fun execute(): Subject {
        logService.info("execute() - start")
        val result = subjectRepository.save(subject)
        logService.info("execute() - end")
        return result
    }
}