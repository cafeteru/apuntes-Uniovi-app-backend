package es.uniovi.apuntesuniovi.servicies

import es.uniovi.apuntesuniovi.infrastructure.log.LogService
import es.uniovi.apuntesuniovi.repositories.SubjectRepository
import es.uniovi.apuntesuniovi.repositories.TeachSubjectRegistryRepository
import es.uniovi.apuntesuniovi.repositories.TeachSubjectRepository
import es.uniovi.apuntesuniovi.repositories.UserRepository
import es.uniovi.apuntesuniovi.servicies.dtos.entities.SubjectDto
import es.uniovi.apuntesuniovi.servicies.dtos.entities.TeachSubjectDto
import es.uniovi.apuntesuniovi.servicies.dtos.impl.SubjectDtoAssembler
import es.uniovi.apuntesuniovi.servicies.dtos.impl.TeachSubjectDtoAssembler
import es.uniovi.apuntesuniovi.servicies.commands.subjects.AddTeacherService
import es.uniovi.apuntesuniovi.servicies.commands.subjects.FindAllSubjectsService
import es.uniovi.apuntesuniovi.servicies.commands.subjects.CreateSubjectService
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
    private val subjectDtoAssembler: SubjectDtoAssembler,
    private val teachSubjectDtoAssembler: TeachSubjectDtoAssembler
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
        val result = AddTeacherService(
            subjectRepository, userRepository,
            teachSubjectRepository, teachSubjectRegistryRepository,
            teachSubjectDtoAssembler, subjectId, teacherId, date
        ).execute()
        logService.info("addTeacher(subjectId: $subjectId, teacherId: $teacherId) - end")
        return result
    }

    /**
     * Create a subject
     *
     * @param subjectDto Subject to save
     */
    fun create(subjectDto: SubjectDto): List<SubjectDto> {
        logService.info("create(subjectDto: SubjectDto) - start")
        val result = CreateSubjectService(subjectRepository, subjectDtoAssembler, subjectDto).execute()
        logService.info("create(subjectDto: SubjectDto) - end")
        return result
    }

    /**
     * Returns all subjects
     */
    fun findAll(): List<SubjectDto> {
        logService.info("findAll() - start")
        val result = FindAllSubjectsService(subjectRepository, subjectDtoAssembler).execute()
        logService.info("findAll() - end")
        return result
    }
}