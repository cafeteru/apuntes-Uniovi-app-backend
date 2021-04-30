package es.uniovi.apuntesuniovi.services

import es.uniovi.apuntesuniovi.dtos.Converter
import es.uniovi.apuntesuniovi.dtos.entities.SubjectDto
import es.uniovi.apuntesuniovi.dtos.entities.TeachSubjectDto
import es.uniovi.apuntesuniovi.dtos.entities.UserDto
import es.uniovi.apuntesuniovi.infrastructure.log.LogService
import es.uniovi.apuntesuniovi.models.TeachSubject
import es.uniovi.apuntesuniovi.repositories.SubjectRepository
import es.uniovi.apuntesuniovi.repositories.TeachSubjectRepository
import es.uniovi.apuntesuniovi.repositories.UserRepository
import es.uniovi.apuntesuniovi.services.commands.teachSubjects.CreateTeachSubject
import es.uniovi.apuntesuniovi.services.commands.teachSubjects.FindSubjectsByTeacherId
import es.uniovi.apuntesuniovi.services.commands.teachSubjects.FindTeachSubjectBySubjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TeachSubjectService @Autowired constructor(
    private val teachSubjectRepository: TeachSubjectRepository,
    private val userRepository: UserRepository,
    private val subjectRepository: SubjectRepository,
) {
    private val logService = LogService(this.javaClass)

    fun create(subjectId: Long, list: List<TeachSubjectDto>): List<TeachSubjectDto> {
        logService.info("create(list: List<TeachSubjectDto>) - start")
        val teachSubjects = Converter.convert(list, TeachSubject::class.java)
        val result = CreateTeachSubject(
            teachSubjectRepository,
            userRepository,
            subjectRepository,
            subjectId,
            teachSubjects
        ).execute()
        logService.info("create(list: List<TeachSubjectDto>) - end")
        return Converter.convert(result, TeachSubjectDto::class.java)
    }

    fun findTeachersBySubjectId(id: Long): List<UserDto> {
        logService.info("findTeachersBySubjectId(id: Long) - start")
        val result = FindTeachSubjectBySubjectId(teachSubjectRepository, id).execute()
        logService.info("findTeachersBySubjectId(id: Long) - end")
        return result.map { teachSubject -> convertUser(teachSubject) }
    }

    fun findSubjectsByTeacherId(id: Long): List<SubjectDto> {
        logService.info("findTeachersBySubjectId(id: Long) - start")
        val result = FindSubjectsByTeacherId(teachSubjectRepository, id).execute()
        logService.info("findTeachersBySubjectId(id: Long) - end")
        return Converter.convert(result, SubjectDto::class.java)
    }

    private fun convertUser(teachSubject: TeachSubject): UserDto {
        val user = Converter.convert(teachSubject.teacher, UserDto::class.java)
        user.password = null
        return user
    }
}