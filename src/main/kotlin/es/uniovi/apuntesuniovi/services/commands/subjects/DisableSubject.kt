package es.uniovi.apuntesuniovi.services.commands.subjects

import es.uniovi.apuntesuniovi.infrastructure.AbstractCommand
import es.uniovi.apuntesuniovi.infrastructure.messages.SubjectMessages
import es.uniovi.apuntesuniovi.models.Subject
import es.uniovi.apuntesuniovi.repositories.SubjectRepository
import es.uniovi.apuntesuniovi.validators.impl.ValidatorId
import org.springframework.util.Assert

/**
 * Change the value active of a subject
 */
class DisableSubject(
    private val subjectRepository: SubjectRepository,
    private val id: Long,
    private val active: Boolean
) : AbstractCommand<Subject>() {

    override fun execute(): Subject {
        Assert.isTrue(ValidatorId(id).isValid(), SubjectMessages.INVALID_ID)
        val optional = subjectRepository.findById(id)
        Assert.isTrue(optional.isPresent, SubjectMessages.NOT_FOUND)
        val subject = optional.get()
        subject.active = active
        return subjectRepository.save(subject)
    }

}