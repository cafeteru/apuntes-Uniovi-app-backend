package es.uniovi.apuntesuniovi.servicies.impl.subjects

import es.uniovi.apuntesuniovi.infrastructure.Command
import es.uniovi.apuntesuniovi.log.LogService
import es.uniovi.apuntesuniovi.repositories.RepositoryFactory
import es.uniovi.apuntesuniovi.servicies.dtos.DtoFactory
import es.uniovi.apuntesuniovi.servicies.dtos.entities.SubjectDto

class SaveSubjectService(
        private val repositoryFactory: RepositoryFactory,
        private val dtoFactory: DtoFactory,
        private val subjectDto: SubjectDto
) : Command<List<SubjectDto>> {
    private val logService = LogService(this.javaClass)

    override fun execute(): List<SubjectDto> {
        logService.info("execute() - start")
        val list = ArrayList<SubjectDto>()
        val subject = dtoFactory.getSubjects().dtoToEntity(subjectDto)
        val result = repositoryFactory.getSubjects().save(subject)
        list.add(dtoFactory.getSubjects().entityToDto(result))
        logService.info("execute() - end")
        return list
    }
}