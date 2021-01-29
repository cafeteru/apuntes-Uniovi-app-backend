package es.uniovi.apuntesuniovi.controllers.commands.users

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import es.uniovi.apuntesuniovi.controllers.commands.BaseCreate
import es.uniovi.apuntesuniovi.infrastructure.AbstractCommand
import es.uniovi.apuntesuniovi.infrastructure.messages.UserMessages
import es.uniovi.apuntesuniovi.models.User
import es.uniovi.apuntesuniovi.services.UserService
import es.uniovi.apuntesuniovi.services.dtos.entities.UserDto

/**
 * Save a user in controller layer
 */
class CreateUser(
  private val userService: UserService,
  private val json: String
) : AbstractCommand<UserDto>() {

  override fun execute(): UserDto {
    logService.info("execute() - start")
    try {
      val dto = Gson().fromJson(json, UserDto::class.java)
      val result = userService.create(dto)
      logService.info("execute() - end")
      return result
    } catch (e: JsonSyntaxException) {
      logService.error("execute() - error")
      throw IllegalArgumentException(UserMessages.INVALID_JSON)
    }
  }
}