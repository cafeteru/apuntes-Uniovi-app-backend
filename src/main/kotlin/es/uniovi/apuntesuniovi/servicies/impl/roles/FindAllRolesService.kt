package es.uniovi.apuntesuniovi.servicies.impl.roles

import es.uniovi.apuntesuniovi.infrastructure.Command
import es.uniovi.apuntesuniovi.log.LogService
import es.uniovi.apuntesuniovi.repositories.RoleRepository
import es.uniovi.apuntesuniovi.servicies.dtos.entities.RoleDto
import es.uniovi.apuntesuniovi.servicies.dtos.impl.RoleDtoAssembler

class FindAllRolesService(
        private val roleRepository: RoleRepository,
        private val roleDtoAssembler: RoleDtoAssembler
) : Command<List<RoleDto>> {
    private val logService = LogService(this.javaClass)

    override fun execute(): List<RoleDto> {
        logService.info("execute() - start")
        val list = roleRepository.findAll()
        val result = roleDtoAssembler.listToDto(list)
        logService.info("execute() - end")
        return result
    }
}