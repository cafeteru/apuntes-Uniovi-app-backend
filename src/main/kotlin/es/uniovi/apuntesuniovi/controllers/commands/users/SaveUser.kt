package es.uniovi.apuntesuniovi.controllers.commands.users

import com.google.gson.Gson
import es.uniovi.apuntesuniovi.infrastructure.Command
import es.uniovi.apuntesuniovi.infrastructure.log.LogService
import es.uniovi.apuntesuniovi.servicies.dtos.entities.UserDto
import es.uniovi.apuntesuniovi.servicies.UserService

class SaveUser(
        private val userService: UserService,
        private val json: String
) : Command<List<UserDto>> {
    private val logService = LogService(this.javaClass)

    override fun execute(): List<UserDto> {
        logService.info("execute() - start")
        val userDto = Gson().fromJson(json, UserDto::class.java)
        val result = userService.create(userDto)
        logService.info("execute() - end")
        return result
    }

}