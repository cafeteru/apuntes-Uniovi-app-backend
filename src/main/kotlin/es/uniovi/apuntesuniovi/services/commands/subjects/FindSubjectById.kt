package es.uniovi.apuntesuniovi.services.commands.subjects

import es.uniovi.apuntesuniovi.infrastructure.messages.SubjectMessages
import es.uniovi.apuntesuniovi.models.Subject
import es.uniovi.apuntesuniovi.repositories.SubjectRepository
import es.uniovi.apuntesuniovi.services.commands.BaseFindById

/**
 * Return subject by id in service layer
 */
class FindSubjectById(
    subjectRepository: SubjectRepository,
    id: Long
) : BaseFindById<Subject>(subjectRepository, id) {

    override fun getMessageNotFound(): String {
        return SubjectMessages.NOT_FOUND
    }

    override fun getMessageInvalidId(): String {
        return SubjectMessages.INVALID_ID
    }
}