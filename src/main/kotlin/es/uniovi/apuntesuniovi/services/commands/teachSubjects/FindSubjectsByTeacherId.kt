package es.uniovi.apuntesuniovi.services.commands.teachSubjects

import es.uniovi.apuntesuniovi.infrastructure.AbstractCommand
import es.uniovi.apuntesuniovi.infrastructure.messages.TeachSubjectMessages
import es.uniovi.apuntesuniovi.models.Subject
import es.uniovi.apuntesuniovi.repositories.TeachSubjectRepository
import es.uniovi.apuntesuniovi.validators.impl.ValidatorId
import org.springframework.util.Assert

class FindSubjectsByTeacherId(
    private val teachSubjectRepository: TeachSubjectRepository,
    private val id: Long
) : AbstractCommand<List<Subject>>() {

    override fun execute(): List<Subject> {
        logService.info("execute(): List<TeachSubject> - start")
        Assert.isTrue(ValidatorId(id).isValid(), TeachSubjectMessages.INVALID_TEACHER_ID)
        val list = teachSubjectRepository.findByTeacherId(id)
        logService.info("execute(): List<TeachSubject> - end")
        return list.map { x -> x.subject }
    }
}