package es.uniovi.apuntesuniovi.services.commands.users

import es.uniovi.apuntesuniovi.infrastructure.AbstractCommand
import es.uniovi.apuntesuniovi.infrastructure.messages.UserMessages
import es.uniovi.apuntesuniovi.repositories.UserRepository

/**
 * Change a user's language
 */
class ChangeLanguageUser(
  private val userRepository: UserRepository,
  private val username: String,
  private val language: String
) : AbstractCommand<Boolean>() {

  override fun execute(): Boolean {
    logService.info("execute() - start")
    val optional = userRepository.findByUsername(username)
    if (optional.isPresent) {
      val user = optional.get()
      user.setLanguage(language)
      userRepository.save(user)
      logService.info("execute() - end")
      return true
    }
    logService.error("execute() - error")
    throw IllegalArgumentException(UserMessages.NOT_FOUND)
  }
}