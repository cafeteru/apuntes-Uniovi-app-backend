package es.uniovi.apuntesuniovi.services

import es.uniovi.apuntesuniovi.dtos.assemblers.LearnSubjectAssembler
import es.uniovi.apuntesuniovi.dtos.assemblers.UserAssembler
import es.uniovi.apuntesuniovi.dtos.entities.LearnSubjectDto
import es.uniovi.apuntesuniovi.dtos.entities.UserDto
import es.uniovi.apuntesuniovi.infrastructure.log.LogService
import es.uniovi.apuntesuniovi.models.LearnSubject
import es.uniovi.apuntesuniovi.repositories.LearnSubjectRepository
import es.uniovi.apuntesuniovi.repositories.SubjectRepository
import es.uniovi.apuntesuniovi.repositories.UserRepository
import es.uniovi.apuntesuniovi.services.commands.learnSubjects.CreateLearnSubject
import es.uniovi.apuntesuniovi.services.commands.learnSubjects.FindLearnSubjectBySubjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class LearnSubjectService @Autowired constructor(
    userRepository: UserRepository,
    subjectRepository: SubjectRepository,
    private val learnSubjectRepository: LearnSubjectRepository
) {
    private val logService = LogService(this.javaClass)
    private val learnSubjectAssembler = LearnSubjectAssembler(
        subjectRepository, userRepository
    )
    private val userAssembler = UserAssembler()

    fun create(subjectId: Long, list: List<LearnSubjectDto>): List<LearnSubjectDto> {
        logService.info("create(list: List<LearnSubjectDto>) - start")
        val learnSubjects = list.map { dto -> learnSubjectAssembler.dtoToEntity(dto) }
        val result = CreateLearnSubject(learnSubjectRepository, subjectId, learnSubjects).execute()
        logService.info("create(list: List<LearnSubjectDto>) - end")
        return result.map { learnSubject -> learnSubjectAssembler.entityToDto(learnSubject) }
    }

    fun findStudentsBySubjectId(id: Long, pageable: Pageable): Page<UserDto> {
        logService.info("findLearnersBySubjectId(id: Long) - start")
        val result = FindLearnSubjectBySubjectId(learnSubjectRepository, id, pageable).execute()
        logService.info("findLearnersBySubjectId(id: Long) - end")
        return result.map { learnSubject -> convertUser(learnSubject) }
    }

    private fun convertUser(learnSubject: LearnSubject): UserDto {
        val user = userAssembler.entityToDto(learnSubject.student)
        user.password = null
        return user
    }
}