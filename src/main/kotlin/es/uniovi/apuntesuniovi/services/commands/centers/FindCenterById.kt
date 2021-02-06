package es.uniovi.apuntesuniovi.services.commands.centers

import es.uniovi.apuntesuniovi.infrastructure.messages.CenterMessages
import es.uniovi.apuntesuniovi.models.Center
import es.uniovi.apuntesuniovi.repositories.CenterRepository
import es.uniovi.apuntesuniovi.services.commands.BaseFindByIdService

/**
 * Return center by id in service layer
 */
class FindCenterById(
  centerRepository: CenterRepository,
  id: Long
) : BaseFindByIdService<Center>(centerRepository, id) {
  override fun getMessageNotFound(): String {
    return CenterMessages.NOT_FOUND
  }

  override fun getMessageInvalidId(): String {
    return CenterMessages.INVALID_ID
  }
}