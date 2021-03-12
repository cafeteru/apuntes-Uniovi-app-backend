package es.uniovi.apuntesuniovi.services.commands.users

import es.uniovi.apuntesuniovi.infrastructure.AbstractCommand
import es.uniovi.apuntesuniovi.infrastructure.messages.UserMessages
import es.uniovi.apuntesuniovi.models.User
import es.uniovi.apuntesuniovi.repositories.UserRepository
import es.uniovi.apuntesuniovi.validators.impl.ValidatorId

/**
 * Delete a user
 */
class DeleteUser(
  private val userRepository: UserRepository,
  private val id: Long,
) : AbstractCommand<Void>() {

  override fun execute(): Void {
    if (ValidatorId(id).isValid()) {
      val optional = userRepository.findById(id)
      if (optional.isPresent) {
        userRepository.deleteById(id)
      }
      throw IllegalArgumentException(UserMessages.NOT_FOUND)
    }
    throw IllegalArgumentException(UserMessages.INVALID_ID)
  }

}