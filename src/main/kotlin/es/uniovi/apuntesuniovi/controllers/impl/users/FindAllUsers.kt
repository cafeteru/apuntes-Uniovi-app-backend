package es.uniovi.apuntesuniovi.controllers.impl.users

import es.uniovi.apuntesuniovi.infrastructure.Command
import es.uniovi.apuntesuniovi.log.LogService
import es.uniovi.apuntesuniovi.servicies.ServiceFactory
import es.uniovi.apuntesuniovi.servicies.dtos.entities.UserDto

class FindAllUsers(
        private val serviceFactory: ServiceFactory
) : Command<List<UserDto>> {
    private val logService = LogService(this.javaClass)

    override fun execute(): List<UserDto> {
        logService.info("execute() - start")
        val result = serviceFactory.getUsers().findAll()
        logService.info("execute() - end")
        return result
    }

}