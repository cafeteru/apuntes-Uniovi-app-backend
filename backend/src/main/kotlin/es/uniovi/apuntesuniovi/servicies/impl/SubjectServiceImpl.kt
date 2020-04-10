package es.uniovi.apuntesuniovi.servicies.impl

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

    override fun findAll(): List<SubjectDto> {
        return FindAllSubjectsService(repositoryFactory, dtoFactory).execute()
    }

    override fun save(subjectDto: SubjectDto): List<SubjectDto> {
        return SaveSubjectService(repositoryFactory, dtoFactory, subjectDto).execute()
    }
}