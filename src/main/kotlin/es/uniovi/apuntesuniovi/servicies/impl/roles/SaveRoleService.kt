package es.uniovi.apuntesuniovi.servicies.impl.roles

import es.uniovi.apuntesuniovi.infrastructure.Command
import es.uniovi.apuntesuniovi.log.LogService
import es.uniovi.apuntesuniovi.repositories.RepositoryFactory
import es.uniovi.apuntesuniovi.servicies.dtos.DtoFactory
import es.uniovi.apuntesuniovi.servicies.dtos.entities.RoleDto

class SaveRoleService(
        private val repositoryFactory: RepositoryFactory,
        private val dtoFactory: DtoFactory,
        private val roleDto: RoleDto
) : Command<List<RoleDto>> {
    private val logService = LogService(this.javaClass)

    override fun execute(): List<RoleDto> {
        logService.info("execute() - start")
        val list = ArrayList<RoleDto>()
        val role = dtoFactory.getRoles().dtoToEntity(roleDto)
        val result = repositoryFactory.getRoles().save(role)
        list.add(dtoFactory.getRoles().entityToDto(result))
        logService.info("execute() - end")
        return list
    }
}