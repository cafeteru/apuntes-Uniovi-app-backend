package es.uniovi.apuntesuniovi.controllers.impl.users

import com.google.gson.Gson
import es.uniovi.apuntesuniovi.infrastructure.Command
import es.uniovi.apuntesuniovi.log.LogService
import es.uniovi.apuntesuniovi.servicies.ServiceFactory
import es.uniovi.apuntesuniovi.servicies.dtos.entities.UserDto

class SaveUser(
        private val serviceFactory: ServiceFactory,
        private val json: String?
) : Command<List<UserDto>> {
    private val logService = LogService(this.javaClass)

    override fun execute(): List<UserDto> {
        logService.info("execute() - start")
        if (json.isNullOrEmpty()) {
            throw IllegalArgumentException("")
        }
        val userDto = Gson().fromJson(json, UserDto::class.java)
        val result = serviceFactory.getUsers().save(userDto)
        logService.info("execute() - end")
        return result
    }

}