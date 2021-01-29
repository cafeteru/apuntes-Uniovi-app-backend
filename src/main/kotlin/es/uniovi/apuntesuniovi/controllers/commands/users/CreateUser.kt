package es.uniovi.apuntesuniovi.controllers.commands.users

import com.google.gson.Gson
import es.uniovi.apuntesuniovi.controllers.commands.BaseCreate
import es.uniovi.apuntesuniovi.infrastructure.messages.UserMessages
import es.uniovi.apuntesuniovi.models.User
import es.uniovi.apuntesuniovi.services.UserService
import es.uniovi.apuntesuniovi.services.dtos.entities.UserDto

/**
 * Save a user in controller layer
 */
class CreateUser(
  userService: UserService,
  json: String
) : BaseCreate<User, UserDto>(userService, json) {

  override fun getEntityFromJson(json: String): UserDto {
    return Gson().fromJson(json, UserDto::class.java)
  }

  override fun getMessageInvalidJson(): String {
    return UserMessages.INVALID_JSON
  }
}