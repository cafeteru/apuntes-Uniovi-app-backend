package es.uniovi.apuntesuniovi.controllers.impl.roles

import es.uniovi.apuntesuniovi.infrastructure.Command
import es.uniovi.apuntesuniovi.log.LogService
import es.uniovi.apuntesuniovi.servicies.ServiceFactory
import es.uniovi.apuntesuniovi.servicies.dtos.entities.RoleDto

class FindAllRoles(private val serviceFactory: ServiceFactory) : Command<List<RoleDto>> {
    private val logService = LogService(this.javaClass)

    override fun execute(): List<RoleDto> {
        logService.info("execute() - start")
        val result = serviceFactory.getRoles().findAll()
        logService.info("execute() - end")
        return result
    }

}