package es.uniovi.apuntesuniovi.servicies.impl

import es.uniovi.apuntesuniovi.repositories.RepositoryFactory
import es.uniovi.apuntesuniovi.servicies.PersonService
import es.uniovi.apuntesuniovi.servicies.dtos.DtoFactory
import es.uniovi.apuntesuniovi.servicies.dtos.entities.PersonDto
import es.uniovi.apuntesuniovi.servicies.impl.persons.FindPersonByUsername
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PersonServiceImpl @Autowired constructor(
        private val repositoryFactory: RepositoryFactory,
        private val dtoFactory: DtoFactory
) : PersonService {
    override fun findByUsername(username: String): PersonDto {
        return FindPersonByUsername(repositoryFactory, dtoFactory, username).execute()
    }
}