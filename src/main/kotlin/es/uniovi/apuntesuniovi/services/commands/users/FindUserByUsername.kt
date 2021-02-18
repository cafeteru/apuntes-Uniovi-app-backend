package es.uniovi.apuntesuniovi.services.commands.users

import es.uniovi.apuntesuniovi.infrastructure.AbstractCommand
import es.uniovi.apuntesuniovi.infrastructure.messages.UserMessages
import es.uniovi.apuntesuniovi.models.User
import es.uniovi.apuntesuniovi.repositories.UserRepository
import io.jsonwebtoken.lang.Assert
import java.util.*

/**
 * Find user by username in service layer
 */
class FindUserByUsername(
  private val userRepository: UserRepository,
  private val username: String?
) : AbstractCommand<User>() {

  override fun execute(): User {
    logService.info("execute() - start")
    Assert.isTrue(!username.isNullOrBlank(), UserMessages.INVALID_USERNAME)
    val optional = userRepository.findByUsername(username!!)
    Assert.isTrue(optional.isPresent, UserMessages.NOT_FOUND_USERNAME)
    logService.info("execute() - end")
    return optional.get()
  }
}