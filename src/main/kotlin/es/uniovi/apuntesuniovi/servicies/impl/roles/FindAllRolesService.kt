package es.uniovi.apuntesuniovi.servicies.impl.subjects

import es.uniovi.apuntesuniovi.infrastructure.Command
import es.uniovi.apuntesuniovi.log.LogService
import es.uniovi.apuntesuniovi.repositories.RepositoryFactory
import es.uniovi.apuntesuniovi.servicies.dtos.DtoFactory
import es.uniovi.apuntesuniovi.servicies.dtos.entities.SubjectDto

class FindAllSubjectsService(
        private val repositoryFactory: RepositoryFactory,
        private val dtoFactory: DtoFactory
) : Command<List<SubjectDto>> {
    private val logService = LogService(this.javaClass)

    override fun execute(): List<SubjectDto> {
        logService.info("execute() - start")
        val list = repositoryFactory.getSubjects().findAll()
        val result = dtoFactory.getSubjects().listToDto(list)
        logService.info("execute() - end")
        return result
    }
}