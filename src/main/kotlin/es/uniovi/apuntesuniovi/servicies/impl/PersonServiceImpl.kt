package es.uniovi.apuntesuniovi.servicies.impl

import es.uniovi.apuntesuniovi.log.LogService
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
    private val logService = LogService(this.javaClass)

    override fun findByUsername(username: String): PersonDto {
        logService.info("findByUsername(username: ${username}) - start")
        val result = FindPersonByUsername(repositoryFactory, dtoFactory, username).execute()
        logService.info("findByUsername(username: ${username}) - end")
        return result
    }
}