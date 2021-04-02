package es.uniovi.apuntesuniovi.services.commands.subjects

import es.uniovi.apuntesuniovi.infrastructure.messages.SubjectMessages
import es.uniovi.apuntesuniovi.models.Subject
import es.uniovi.apuntesuniovi.repositories.SubjectRepository
import es.uniovi.apuntesuniovi.services.commands.BaseDelete

/**
 * Delete a Subject
 */
class DeleteSubject(
    subjectRepository: SubjectRepository,
    id: Long,
) : BaseDelete<Subject>(subjectRepository, id) {

    override fun getMessageInvalidId(): String {
        return SubjectMessages.INVALID_ID
    }

    override fun getMessageNotFound(): String {
        return SubjectMessages.NOT_FOUND
    }

}