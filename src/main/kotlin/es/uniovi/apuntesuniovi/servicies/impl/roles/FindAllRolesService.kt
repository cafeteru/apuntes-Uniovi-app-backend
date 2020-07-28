package es.uniovi.apuntesuniovi.servicies.impl.roles

import es.uniovi.apuntesuniovi.infrastructure.Command
import es.uniovi.apuntesuniovi.log.LogService
import es.uniovi.apuntesuniovi.repositories.RepositoryFactory
import es.uniovi.apuntesuniovi.servicies.dtos.DtoFactory
import es.uniovi.apuntesuniovi.servicies.dtos.entities.RoleDto

class FindAllRolesService(
        private val repositoryFactory: RepositoryFactory,
        private val dtoFactory: DtoFactory
) : Command<List<RoleDto>> {
    private val logService = LogService(this.javaClass)

    override fun execute(): List<RoleDto> {
        logService.info("execute() - start")
        val list = repositoryFactory.getRoles().findAll()
        val result = dtoFactory.getRoles().listToDto(list)
        logService.info("execute() - end")
        return result
    }
}