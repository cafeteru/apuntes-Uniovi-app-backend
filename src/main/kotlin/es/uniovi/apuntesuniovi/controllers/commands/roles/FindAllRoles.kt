package es.uniovi.apuntesuniovi.controllers.commands.roles

import es.uniovi.apuntesuniovi.infrastructure.Command
import es.uniovi.apuntesuniovi.log.LogService
import es.uniovi.apuntesuniovi.servicies.RoleService
import es.uniovi.apuntesuniovi.servicies.dtos.entities.RoleDto

class FindAllRoles(
        private val roleService: RoleService
) : Command<List<RoleDto>> {
    private val logService = LogService(this.javaClass)

    override fun execute(): List<RoleDto> {
        logService.info("execute() - start")
        val result = roleService.findAll()
        logService.info("execute() - end")
        return result
    }

}