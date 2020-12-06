package es.uniovi.apuntesuniovi.controllers.commands.users

import com.google.gson.Gson
import es.uniovi.apuntesuniovi.infrastructure.AbstractCommand
import es.uniovi.apuntesuniovi.services.UserService
import es.uniovi.apuntesuniovi.services.dtos.entities.UserDto

/**
 * Save a user in controller layer
 */
class CreateUser(
    private val userService: UserService,
    private val json: String
) : AbstractCommand<List<UserDto>>() {
    override fun execute(): List<UserDto> {
        logService.info("execute() - start")
        val userDto = Gson().fromJson(json, UserDto::class.java)
        val result = userService.create(userDto)
        logService.info("execute() - end")
        return result
    }
}