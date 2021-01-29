package es.uniovi.apuntesuniovi.services.commands.users

import es.uniovi.apuntesuniovi.infrastructure.messages.UserMessages
import es.uniovi.apuntesuniovi.models.User
import es.uniovi.apuntesuniovi.repositories.UserRepository
import es.uniovi.apuntesuniovi.services.commands.BaseFindByIdService

/**
 * Return user by id in service layer
 */
class FindUserByIdService(
  userRepository: UserRepository,
  id: Long
) : BaseFindByIdService<User>(userRepository, id) {
  override fun getMessageNotFound(): String {
    return UserMessages.NOT_FOUND
  }

  override fun getMessageInvalidId(): String {
    return UserMessages.INVALID_ID
  }
}