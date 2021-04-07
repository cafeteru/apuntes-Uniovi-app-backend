package es.uniovi.apuntesuniovi.services

import es.uniovi.apuntesuniovi.dtos.assemblers.TeachSubjectAssembler
import es.uniovi.apuntesuniovi.dtos.assemblers.UserAssembler
import es.uniovi.apuntesuniovi.dtos.entities.TeachSubjectDto
import es.uniovi.apuntesuniovi.dtos.entities.UserDto
import es.uniovi.apuntesuniovi.infrastructure.log.LogService
import es.uniovi.apuntesuniovi.models.TeachSubject
import es.uniovi.apuntesuniovi.repositories.SubjectRepository
import es.uniovi.apuntesuniovi.repositories.TeachSubjectRepository
import es.uniovi.apuntesuniovi.repositories.UserRepository
import es.uniovi.apuntesuniovi.services.commands.teachSubjects.CreateTeachSubject
import es.uniovi.apuntesuniovi.services.commands.teachSubjects.FindTeachSubjectBySubjectId
import es.uniovi.apuntesuniovi.services.commands.teachSubjects.UpdateTeachSubject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TeachSubjectService @Autowired constructor(
    userRepository: UserRepository,
    subjectRepository: SubjectRepository,
    private val teachSubjectRepository: TeachSubjectRepository
) {
    private val logService = LogService(this.javaClass)
    private val teachSubjectAssembler = TeachSubjectAssembler(
        subjectRepository, userRepository
    )
    private val userAssembler = UserAssembler()

    fun create(list: List<TeachSubjectDto>): List<TeachSubjectDto> {
        logService.info("create(list: List<TeachSubjectDto>) - start")
        val teachSubjects = list.map { dto -> teachSubjectAssembler.dtoToEntity(dto) }
        val result = CreateTeachSubject(teachSubjectRepository, teachSubjects).execute()
        logService.info("create(list: List<TeachSubjectDto>) - end")
        return result.map { teachSubject -> teachSubjectAssembler.entityToDto(teachSubject) }
    }

    fun findTeachersBySubjectId(id: Long): List<UserDto> {
        logService.info("findTeachersBySubjectId(id: Long) - start")
        val result = FindTeachSubjectBySubjectId(teachSubjectRepository, id).execute()
        logService.info("findTeachersBySubjectId(id: Long) - end")
        return result.map { teachSubject -> convertUser(teachSubject) }
    }

    fun update(id: Long, list: List<TeachSubjectDto>): List<TeachSubjectDto> {
        logService.info("update(id: Long, list: List<TeachSubjectDto>) - start")
        val teachSubjects = list.map { dto -> teachSubjectAssembler.dtoToEntity(dto) }
        val result = UpdateTeachSubject(teachSubjectRepository, id, teachSubjects).execute()
        logService.info("create(id: Long, list: List<TeachSubjectDto>) - end")
        return result.map { teachSubject -> teachSubjectAssembler.entityToDto(teachSubject) }
    }

    private fun convertUser(teachSubject: TeachSubject): UserDto {
        val user = userAssembler.entityToDto(teachSubject.teacher)
        user.password = null
        return user
    }
}