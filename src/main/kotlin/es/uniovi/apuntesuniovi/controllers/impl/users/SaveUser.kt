package es.uniovi.apuntesuniovi.controllers.impl.subjects

import com.google.gson.Gson
import es.uniovi.apuntesuniovi.infrastructure.Command
import es.uniovi.apuntesuniovi.log.LogService
import es.uniovi.apuntesuniovi.servicies.ServiceFactory
import es.uniovi.apuntesuniovi.servicies.dtos.entities.RoleDto

class SaveRole(
        private val serviceFactory: ServiceFactory,
        private val json: String?
) : Command<List<RoleDto>> {
    private val logService = LogService(this.javaClass)

    override fun execute(): List<RoleDto> {
        logService.info("execute() - start")
        if (json == null || json.isEmpty()) {
            throw IllegalArgumentException("")
        }
        val roleDto = Gson().fromJson(json, RoleDto::class.java)
        val result = serviceFactory.getRoles().save(roleDto)
        logService.info("execute() - end")
        return result
    }

}