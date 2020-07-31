package es.uniovi.apuntesuniovi.controllers.commands.roles

import com.google.gson.Gson
import es.uniovi.apuntesuniovi.infrastructure.Command
import es.uniovi.apuntesuniovi.log.LogService
import es.uniovi.apuntesuniovi.servicies.RoleService
import es.uniovi.apuntesuniovi.servicies.dtos.entities.RoleDto

class SaveRole(
        private val roleService: RoleService,
        private val json: String
) : Command<List<RoleDto>> {
    private val logService = LogService(this.javaClass)

    override fun execute(): List<RoleDto> {
        logService.info("execute() - start")
        val roleDto = Gson().fromJson(json, RoleDto::class.java)
        val result = roleService.save(roleDto)
        logService.info("execute() - end")
        return result
    }

}