package es.uniovi.apuntesuniovi.servicies.impl.roles

import es.uniovi.apuntesuniovi.infrastructure.Command
import es.uniovi.apuntesuniovi.log.LogService
import es.uniovi.apuntesuniovi.repositories.RoleRepository
import es.uniovi.apuntesuniovi.servicies.dtos.entities.RoleDto
import es.uniovi.apuntesuniovi.servicies.dtos.impl.RoleDtoAssembler

class SaveRoleService(
        private val roleRepository: RoleRepository,
        private val roleDtoAssembler: RoleDtoAssembler,
        private val roleDto: RoleDto
) : Command<List<RoleDto>> {
    private val logService = LogService(this.javaClass)

    override fun execute(): List<RoleDto> {
        logService.info("execute() - start")
        val list = ArrayList<RoleDto>()
        val role = roleDtoAssembler.dtoToEntity(roleDto)
        val result = roleRepository.save(role)
        list.add(roleDtoAssembler.entityToDto(result))
        logService.info("execute() - end")
        return list
    }
}