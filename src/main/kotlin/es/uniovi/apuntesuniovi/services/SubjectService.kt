package es.uniovi.apuntesuniovi.services

import es.uniovi.apuntesuniovi.models.Subject
import es.uniovi.apuntesuniovi.repositories.*
import es.uniovi.apuntesuniovi.services.commands.subjects.AddTeacherService
import es.uniovi.apuntesuniovi.services.commands.subjects.CreateSubjectService
import es.uniovi.apuntesuniovi.services.commands.subjects.FindAllSubjectsService
import es.uniovi.apuntesuniovi.services.commands.subjects.FindSubjectByIdService
import es.uniovi.apuntesuniovi.services.commands.teachSubjectRegistries.CreateTeachSubjectRegistryService
import es.uniovi.apuntesuniovi.services.commands.users.FindUserByIdService
import es.uniovi.apuntesuniovi.services.dtos.assemblers.SubjectAssembler
import es.uniovi.apuntesuniovi.services.dtos.assemblers.TeachSubjectAssembler
import es.uniovi.apuntesuniovi.services.dtos.entities.SubjectDto
import es.uniovi.apuntesuniovi.services.dtos.entities.TeachSubjectDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.time.LocalDate

/**
 * Service to manage subjects
 */
@Service
class SubjectService @Autowired constructor(
    private val subjectRepository: SubjectRepository,
    private val userRepository: UserRepository,
    private val teachSubjectRepository: TeachSubjectRepository,
    private val teachSubjectRegistryRepository: TeachSubjectRegistryRepository,
    subjectAssembler: SubjectAssembler,
    private val teachSubjectDtoAssembler: TeachSubjectAssembler
) : BaseService<Subject, SubjectDto>(subjectRepository, subjectAssembler) {

    /**
     * Add a teacher into the subject
     *
     * @param subjectId The subject's id
     * @param teacherId The teacher's id
     * @param date The date it started
     */
    fun addTeacher(subjectId: Long, teacherId: Long, date: LocalDate): TeachSubjectDto {
        logService.info("addTeacher(subjectId: $subjectId, teacherId: $teacherId) - start")
        val subject = FindSubjectByIdService(subjectRepository, teacherId).execute()[0]
        val teacher = FindUserByIdService(userRepository, teacherId).execute()[0]
        val teachSubject = AddTeacherService(teachSubjectRepository, subject, teacher).execute()[0]
        CreateTeachSubjectRegistryService(teachSubjectRegistryRepository, teachSubject, date).execute()
        logService.info("addTeacher(subjectId: $subjectId, teacherId: $teacherId) - end")
        return teachSubjectDtoAssembler.entityToDto(teachSubject)
    }

    override fun create(
        repository: PageableRepository<Subject>,
        entity: Subject
    ): List<Subject> {
        return CreateSubjectService(subjectRepository, entity).execute()
    }

    override fun findAll(
        repository: PageableRepository<Subject>,
        pageable: Pageable
    ): Page<Subject> {
        return FindAllSubjectsService(subjectRepository, pageable).execute()
    }
}