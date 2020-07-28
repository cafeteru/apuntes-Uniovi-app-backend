package es.uniovi.apuntesuniovi.servicies.impl.users

import es.uniovi.apuntesuniovi.infrastructure.Command
import es.uniovi.apuntesuniovi.log.LogService
import es.uniovi.apuntesuniovi.repositories.RepositoryFactory
import es.uniovi.apuntesuniovi.servicies.dtos.DtoFactory
import es.uniovi.apuntesuniovi.servicies.dtos.entities.UserDto

class FindAllUsersService(
        private val repositoryFactory: RepositoryFactory,
        private val dtoFactory: DtoFactory
) : Command<List<UserDto>> {
    private val logService = LogService(this.javaClass)

    override fun execute(): List<UserDto> {
        logService.info("execute() - start")
        val list = repositoryFactory.getUsers().findAll()
        val result = dtoFactory.getUsers().listToDto(list)
        logService.info("execute() - end")
        return result
    }
}