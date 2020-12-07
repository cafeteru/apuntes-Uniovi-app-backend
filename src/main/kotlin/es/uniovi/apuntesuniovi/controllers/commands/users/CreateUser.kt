package es.uniovi.apuntesuniovi.controllers.commands.users

import com.google.gson.Gson
import es.uniovi.apuntesuniovi.controllers.commands.AbstractCreate
import es.uniovi.apuntesuniovi.services.UserService
import es.uniovi.apuntesuniovi.services.dtos.entities.UserDto

/**
 * Save a user in controller layer
 */
class CreateUser(
    userService: UserService,
    json: String
) : AbstractCreate<UserDto>(userService, json) {

    override fun getEntityFromJson(json: String): UserDto {
        logService.info("getEntityFromJson(json: String) - start")
        val dto = Gson().fromJson(json, UserDto::class.java)
        logService.info("getEntityFromJson(json: String) - end")
        return dto
    }
}