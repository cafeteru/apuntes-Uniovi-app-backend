package es.uniovi.apuntesuniovi.dtos.assemblers

import es.uniovi.apuntesuniovi.dtos.entities.LearnSubjectDto
import es.uniovi.apuntesuniovi.infrastructure.messages.LearnSubjectMessages
import es.uniovi.apuntesuniovi.models.LearnSubject
import es.uniovi.apuntesuniovi.models.types.RoleType
import es.uniovi.apuntesuniovi.repositories.SubjectRepository
import es.uniovi.apuntesuniovi.repositories.UserRepository
import es.uniovi.apuntesuniovi.services.commands.subjects.FindSubjectById
import es.uniovi.apuntesuniovi.services.commands.users.FindUserById
import org.springframework.util.Assert

/**
 * Define the entity and dto conversion methods of LearnSubjects
 */
class LearnSubjectAssembler(
    private val subjectRepository: SubjectRepository,
    private val userRepository: UserRepository
) : AbstractAssembler<LearnSubject, LearnSubjectDto>() {
    override fun entityToDto(entity: LearnSubject?): LearnSubjectDto {
        logService.info("entityToDto(entity: LearnSubject) - start")
        entity?.let {
            val result = LearnSubjectDto(
                id = it.id,
                subjectId = it.subject.id!!,
                studentId= it.student.id!!
            )
            logService.info("entityToDto(entity: LearnSubject) - end")
            return result
        }
        throw IllegalArgumentException(LearnSubjectMessages.NULL)
    }

    override fun dtoToEntity(dto: LearnSubjectDto?): LearnSubject {
        logService.info("dtoToEntity(dto: LearnSubjectDto) - start")
        dto?.let {
            val result = LearnSubject()
            result.id = it.id
            result.subject = FindSubjectById(subjectRepository, it.subjectId).execute()
            result.student = FindUserById(userRepository, it.studentId).execute()
            Assert.isTrue(result.student.role == RoleType.STUDENT,
                LearnSubjectMessages.INVALID_USER_ROLE)
            logService.info("dtoToEntity(dto: LearnSubjectDto) - end")
            return result
        }
        throw IllegalArgumentException(LearnSubjectMessages.NULL)
    }
}