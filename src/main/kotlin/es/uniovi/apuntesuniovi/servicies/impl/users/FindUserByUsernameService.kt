package es.uniovi.apuntesuniovi.servicies.impl.users

import es.uniovi.apuntesuniovi.entities.User
import es.uniovi.apuntesuniovi.infrastructure.Command
import es.uniovi.apuntesuniovi.log.LogService
import es.uniovi.apuntesuniovi.repositories.RepositoryFactory
import es.uniovi.apuntesuniovi.servicies.dtos.DtoFactory
import es.uniovi.apuntesuniovi.servicies.dtos.entities.UserDto
import java.util.*

class FindUserByUsernameService(
        private val repositoryFactory: RepositoryFactory,
        private val dtoFactory: DtoFactory,
        private val username: String
) : Command<UserDto> {
    private val logService = LogService(this.javaClass)

    override fun execute(): UserDto {
        logService.info("execute() - start")
        val optional: Optional<User> = repositoryFactory.getUsers().findByUsername(username)
        if (optional.isPresent) {
            logService.info("execute() - end")
            return dtoFactory.getUsers().entityToDto(optional.get())
        }
        throw IllegalArgumentException("No hay ningun usuario registrado con $username")
    }
}