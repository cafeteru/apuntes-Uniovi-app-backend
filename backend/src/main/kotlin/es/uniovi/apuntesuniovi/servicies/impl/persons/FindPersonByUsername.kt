package es.uniovi.apuntesuniovi.servicies.impl.persons

import es.uniovi.apuntesuniovi.entities.Person
import es.uniovi.apuntesuniovi.infrastructure.Command
import es.uniovi.apuntesuniovi.repositories.RepositoryFactory
import es.uniovi.apuntesuniovi.servicies.dtos.DtoFactory
import es.uniovi.apuntesuniovi.servicies.dtos.entities.PersonDto
import java.util.*

class FindPersonByUsername(
        private val repositoryFactory: RepositoryFactory,
        private val dtoFactory: DtoFactory,
        private val username: String
) : Command<PersonDto> {
    override fun execute(): PersonDto {
        val optional: Optional<Person> = repositoryFactory.getPersons().findByUsername(username)
        if (optional.isPresent) {
            return dtoFactory.getPersons().entityToDto(optional.get())
        }
        throw IllegalArgumentException("No hay ningun usuario registrado con $username")
    }
}