package es.uniovi.apuntesuniovi.services.commands.teachSubjects

import es.uniovi.apuntesuniovi.infrastructure.messages.TeachSubjectMessages
import es.uniovi.apuntesuniovi.models.TeachSubject
import es.uniovi.apuntesuniovi.repositories.TeachSubjectRepository
import es.uniovi.apuntesuniovi.services.commands.BaseFindByIdService

/**
 * Return teachSubject by id in service layer
 */
class FindTeachSubjectById(
  teachSubjectRepository: TeachSubjectRepository,
  id: Long
) : BaseFindByIdService<TeachSubject>(teachSubjectRepository, id) {
  override fun getMessageNotFound(): String {
    return TeachSubjectMessages.NOT_FOUND
  }

  override fun getMessageInvalidId(): String {
    return TeachSubjectMessages.INVALID_ID
  }
}