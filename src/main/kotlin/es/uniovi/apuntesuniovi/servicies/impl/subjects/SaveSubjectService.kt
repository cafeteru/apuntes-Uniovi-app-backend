package es.uniovi.apuntesuniovi.servicies.impl.subjects

import es.uniovi.apuntesuniovi.infrastructure.Command
import es.uniovi.apuntesuniovi.repositories.RepositoryFactory
import es.uniovi.apuntesuniovi.servicies.dtos.DtoFactory
import es.uniovi.apuntesuniovi.servicies.dtos.entities.SubjectDto

class SaveSubjectService(
        private val repositoryFactory: RepositoryFactory,
        private val dtoFactory: DtoFactory,
        private val subjectDto: SubjectDto
) : Command<List<SubjectDto>> {
    override fun execute(): List<SubjectDto> {
        val list = ArrayList<SubjectDto>()
        val subject = dtoFactory.getSubjects().dtoToEntity(subjectDto)
        val result = repositoryFactory.getSubjects().save(subject)
        list.add(dtoFactory.getSubjects().entityToDto(result))
        return list
    }
}