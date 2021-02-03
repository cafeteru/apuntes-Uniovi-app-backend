package es.uniovi.apuntesuniovi.controllers.commands.users

import es.uniovi.apuntesuniovi.infrastructure.AbstractCommand
import es.uniovi.apuntesuniovi.services.UserService

class ChangeLanguageUser(
  private val userService: UserService,
  private val username: String,
  private val language: String
) : AbstractCommand<Void?>() {

  override fun execute(): Void? {
    logService.info("execute() - start")
    userService.changeLanguage(username, language)
    logService.info("execute() - end")
    return null
  }
}