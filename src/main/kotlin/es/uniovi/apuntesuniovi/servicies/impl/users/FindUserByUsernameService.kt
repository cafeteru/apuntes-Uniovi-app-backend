package es.uniovi.apuntesuniovi.servicies.impl.persons

import es.uniovi.apuntesuniovi.entities.User
import es.uniovi.apuntesuniovi.infrastructure.Command
import es.uniovi.apuntesuniovi.log.LogService
import es.uniovi.apuntesuniovi.repositories.RepositoryFactory
import es.uniovi.apuntesuniovi.servicies.dtos.DtoFactory
import es.uniovi.apuntesuniovi.servicies.dtos.entities.PersonDto
import java.util.*

class FindPersonByUsername(
        private val repositoryFactory: RepositoryFactory,
        private val dtoFactory: DtoFactory,
        private val username: String
) : Command<PersonDto> {
    private val logService = LogService(this.javaClass)

    override fun execute(): PersonDto {
        logService.info("execute() - start")
        val optional: Optional<User> = repositoryFactory.getUsers().findByUsername(username)
        if (optional.isPresent) {
            logService.info("execute() - end")
            return dtoFactory.getPersons().entityToDto(optional.get())
        }
        throw IllegalArgumentException("No hay ningun usuario registrado con $username")
    }
}