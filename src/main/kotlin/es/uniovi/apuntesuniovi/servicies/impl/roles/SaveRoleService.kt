package es.uniovi.apuntesuniovi.servicies.impl.persons

import es.uniovi.apuntesuniovi.infrastructure.Command
import es.uniovi.apuntesuniovi.log.LogService
import es.uniovi.apuntesuniovi.repositories.RepositoryFactory
import es.uniovi.apuntesuniovi.servicies.dtos.DtoFactory
import es.uniovi.apuntesuniovi.servicies.dtos.entities.PersonDto

class SavePersonService(
        private val repositoryFactory: RepositoryFactory,
        private val dtoFactory: DtoFactory,
        private val personDto: PersonDto
) : Command<List<PersonDto>> {
    private val logService = LogService(this.javaClass)

    override fun execute(): List<PersonDto> {
        logService.info("execute() - start")
        val list = ArrayList<PersonDto>()
        val person = dtoFactory.getPersons().dtoToEntity(personDto)
        val result = repositoryFactory.getPersons().save(person)
        list.add(dtoFactory.getPersons().entityToDto(result))
        logService.info("execute() - end")
        return list
    }
}