package es.uniovi.apuntesuniovi.controllers.commands.users

import es.uniovi.apuntesuniovi.infrastructure.Command
import es.uniovi.apuntesuniovi.infrastructure.log.LogService
import es.uniovi.apuntesuniovi.servicies.UserService
import es.uniovi.apuntesuniovi.servicies.dtos.entities.UserDto

/**
 * Return all users since service layer
 */
class FindAllUsers(
    private val userService: UserService
) : Command<List<UserDto>> {
    private val logService = LogService(this.javaClass)

    override fun execute(): List<UserDto> {
        logService.info("execute() - start")
        val result = userService.findAll()
        logService.info("execute() - end")
        return result
    }

}