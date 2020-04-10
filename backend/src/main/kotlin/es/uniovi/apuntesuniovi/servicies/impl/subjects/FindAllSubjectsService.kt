package es.uniovi.apuntesuniovi.servicies.impl.subjects

import es.uniovi.apuntesuniovi.entities.Subject
import es.uniovi.apuntesuniovi.infrastructure.Command
import es.uniovi.apuntesuniovi.repositories.RepositoryFactory
import es.uniovi.apuntesuniovi.servicies.dtos.DtoFactory
import es.uniovi.apuntesuniovi.servicies.dtos.entities.SubjectDto

class FindAllSubjectsService(
        private val repositoryFactory: RepositoryFactory,
        private val dtoFactory: DtoFactory
) : Command<List<SubjectDto>> {
    override fun execute(): List<SubjectDto> {
        val list: List<Subject> = repositoryFactory.getSubjects().findAll()
        return dtoFactory.getSubjects().listToDto(list)
    }
}