package es.uniovi.apuntesuniovi.controllers.commands.users

import es.uniovi.apuntesuniovi.infrastructure.AbstractCommand
import es.uniovi.apuntesuniovi.services.UserService
import es.uniovi.apuntesuniovi.services.dtos.entities.UserDto

/**
 * Return all users in controller layer
 */
class FindAllUsers(
    private val userService: UserService
) : AbstractCommand<List<UserDto>>() {
    override fun execute(): List<UserDto> {
        logService.info("execute() - start")
        val result = userService.findAll()
        logService.info("execute() - end")
        return result
    }
}