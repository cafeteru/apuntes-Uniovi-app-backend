package es.uniovi.apuntesuniovi.services

import es.uniovi.apuntesuniovi.infrastructure.log.LogService
import es.uniovi.apuntesuniovi.repositories.SubjectRepository
import es.uniovi.apuntesuniovi.repositories.TeachSubjectRegistryRepository
import es.uniovi.apuntesuniovi.repositories.TeachSubjectRepository
import es.uniovi.apuntesuniovi.repositories.UserRepository
import es.uniovi.apuntesuniovi.services.commands.subjects.AddTeacherService
import es.uniovi.apuntesuniovi.services.commands.subjects.CreateSubjectService
import es.uniovi.apuntesuniovi.services.commands.subjects.FindAllSubjectsService
import es.uniovi.apuntesuniovi.services.commands.subjects.FindSubjectByIdService
import es.uniovi.apuntesuniovi.services.commands.teachSubjectRegistry.CreateTeachSubjectRegistryService
import es.uniovi.apuntesuniovi.services.commands.users.FindUserByIdService
import es.uniovi.apuntesuniovi.services.dtos.entities.SubjectDto
import es.uniovi.apuntesuniovi.services.dtos.entities.TeachSubjectDto
import es.uniovi.apuntesuniovi.services.dtos.assemblers.SubjectAssembler
import es.uniovi.apuntesuniovi.services.dtos.assemblers.TeachSubjectAssembler
import org.springframework.beans.factory.annotation.Autowired
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
    private val subjectDtoAssembler: SubjectAssembler,
    private val teachSubjectDtoAssembler: TeachSubjectAssembler
) {
    private val logService = LogService(this.javaClass)

    /**
     * Add a teacher into the subject
     *
     * @param subjectId The subject's id
     * @param teacherId The teacher's id
     * @param date The date it started
     */
    fun addTeacher(subjectId: Long, teacherId: Long, date: LocalDate): List<TeachSubjectDto> {
        logService.info("addTeacher(subjectId: $subjectId, teacherId: $teacherId) - start")
        val subject = FindSubjectByIdService(subjectRepository, teacherId).execute()[0]
        val teacher = FindUserByIdService(userRepository, teacherId).execute()[0]
        val teachSubject = AddTeacherService(teachSubjectRepository, subject, teacher).execute()[0]
        CreateTeachSubjectRegistryService(teachSubjectRegistryRepository, teachSubject, date).execute()
        logService.info("addTeacher(subjectId: $subjectId, teacherId: $teacherId) - end")
        return teachSubjectDtoAssembler.listToDto(listOf(teachSubject))
    }

    /**
     * Create a subject
     *
     * @param subjectDto Subject to save
     */
    fun create(subjectDto: SubjectDto): List<SubjectDto> {
        logService.info("create(subjectDto: SubjectDto) - start")
        val subject = subjectDtoAssembler.dtoToEntity(subjectDto)
        val result = CreateSubjectService(subjectRepository, subject).execute()
        logService.info("create(subjectDto: SubjectDto) - end")
        return subjectDtoAssembler.listToDto(result)
    }

    /**
     * Returns all subjects
     */
    fun findAll(): List<SubjectDto> {
        logService.info("findAll() - start")
        val result = FindAllSubjectsService(subjectRepository).execute()
        logService.info("findAll() - end")
        return subjectDtoAssembler.listToDto(result)
    }
}