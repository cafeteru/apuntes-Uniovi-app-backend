package es.uniovi.apuntesuniovi.services.commands.teachSubjects

import es.uniovi.apuntesuniovi.infrastructure.AbstractCommand
import es.uniovi.apuntesuniovi.infrastructure.messages.TeachSubjectMessages
import es.uniovi.apuntesuniovi.models.TeachSubject
import es.uniovi.apuntesuniovi.repositories.TeachSubjectRepository
import es.uniovi.apuntesuniovi.validators.impl.ValidatorId
import org.springframework.util.Assert

/**
 * Return teachSubject by id in service layer
 */
class FindTeachSubjectBySubjectId(
    private val teachSubjectRepository: TeachSubjectRepository,
    private val id: Long
) : AbstractCommand<List<TeachSubject>>() {

    override fun execute(): List<TeachSubject> {
        logService.info("execute(): List<TeachSubject> - start")
        Assert.isTrue(ValidatorId(id).isValid(), TeachSubjectMessages.INVALID_SUBJECT_ID)
        val list = teachSubjectRepository.findBySubjectId(id)
        logService.info("execute(): List<TeachSubject> - end")
        return list
    }
}