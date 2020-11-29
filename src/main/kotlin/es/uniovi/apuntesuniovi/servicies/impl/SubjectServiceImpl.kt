package es.uniovi.apuntesuniovi.servicies.impl

import es.uniovi.apuntesuniovi.log.LogService
import es.uniovi.apuntesuniovi.repositories.RepositoryFactory
import es.uniovi.apuntesuniovi.servicies.SubjectService
import es.uniovi.apuntesuniovi.servicies.dtos.DtoFactory
import es.uniovi.apuntesuniovi.servicies.dtos.entities.SubjectDto
import es.uniovi.apuntesuniovi.servicies.dtos.entities.TeachSubjectDto
import es.uniovi.apuntesuniovi.servicies.impl.subjects.AddTeacherService
import es.uniovi.apuntesuniovi.servicies.impl.subjects.FindAllSubjectsService
import es.uniovi.apuntesuniovi.servicies.impl.subjects.SaveSubjectService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class SubjectServiceImpl @Autowired constructor(
        private val repositoryFactory: RepositoryFactory,
        private val dtoFactory: DtoFactory
) : SubjectService {
    private val logService = LogService(this.javaClass)

    override fun addTeacher(subjectId: Long, teacherId: Long, date: LocalDate): List<TeachSubjectDto> {
        logService.info("addTeacher(subjectId: $subjectId, teacherId: $teacherId) - start")
        val result = AddTeacherService(repositoryFactory.getSubjects(), repositoryFactory.getUsers(),
                repositoryFactory.getTeachSubjects(), repositoryFactory.getTeachSubjectRegistries(),
                dtoFactory.getTeachSubjects(), subjectId, teacherId, date).execute()
        logService.info("addTeacher(subjectId: $subjectId, teacherId: $teacherId) - end")
        return result
    }

    override fun create(subjectDto: SubjectDto): List<SubjectDto> {
        logService.info("create(subjectDto: SubjectDto) - start")
        val result = SaveSubjectService(repositoryFactory.getSubjects(), dtoFactory.getSubjects(), subjectDto).execute()
        logService.info("create(subjectDto: SubjectDto) - end")
        return result
    }

    override fun findAll(): List<SubjectDto> {
        logService.info("findAll() - start")
        val result = FindAllSubjectsService(repositoryFactory.getSubjects(), dtoFactory.getSubjects()).execute()
        logService.info("findAll() - end")
        return result
    }
}