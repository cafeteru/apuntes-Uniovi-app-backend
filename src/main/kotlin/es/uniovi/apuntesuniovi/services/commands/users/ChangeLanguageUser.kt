package es.uniovi.apuntesuniovi.services.commands.users

import es.uniovi.apuntesuniovi.infrastructure.AbstractCommand
import es.uniovi.apuntesuniovi.repositories.UserRepository

class ChangeLanguageUser(
  private val userRepository: UserRepository,
  private val username: String,
  private val language: String
) : AbstractCommand<Void?>() {

  override fun execute(): Void? {
    logService.info("execute() - start")
    val optional = userRepository.findByUsername(username)
    if (optional.isPresent) {
      val user = optional.get()
      user.setLanguage(language)
      userRepository.save(user)
    }
    logService.info("execute() - end")
    return null
  }
}