package es.uniovi.apuntesuniovi.servicies.impl

import es.uniovi.apuntesuniovi.log.LogService
import es.uniovi.apuntesuniovi.repositories.RepositoryFactory
import es.uniovi.apuntesuniovi.servicies.SubjectService
import es.uniovi.apuntesuniovi.servicies.dtos.DtoFactory
import es.uniovi.apuntesuniovi.servicies.dtos.entities.SubjectDto
import es.uniovi.apuntesuniovi.servicies.impl.subjects.FindAllSubjectsService
import es.uniovi.apuntesuniovi.servicies.impl.subjects.SaveSubjectService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SubjectServiceImpl @Autowired constructor(
        private val repositoryFactory: RepositoryFactory,
        private val dtoFactory: DtoFactory
) : SubjectService {
    private val logService = LogService(this.javaClass)

    override fun findAll(): List<SubjectDto> {
        logService.info("findAll() - start")
        val result = FindAllSubjectsService(repositoryFactory.getSubjects(), dtoFactory.getSubjects()).execute()
        logService.info("findAll() - end")
        return result
    }

    override fun save(subjectDto: SubjectDto): List<SubjectDto> {
        logService.info("save(subjectDto: SubjectDto) - start")
        val result = SaveSubjectService(repositoryFactory.getSubjects(), dtoFactory.getSubjects(), subjectDto).execute()
        logService.info("save(subjectDto: SubjectDto) - end")
        return result
    }
}