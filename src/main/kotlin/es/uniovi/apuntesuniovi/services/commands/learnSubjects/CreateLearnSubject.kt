package es.uniovi.apuntesuniovi.services.commands.learnSubjects

import es.uniovi.apuntesuniovi.infrastructure.AbstractCommand
import es.uniovi.apuntesuniovi.infrastructure.messages.LearnSubjectMessages
import es.uniovi.apuntesuniovi.models.LearnSubject
import es.uniovi.apuntesuniovi.models.types.RoleType
import es.uniovi.apuntesuniovi.repositories.LearnSubjectRepository
import es.uniovi.apuntesuniovi.repositories.SubjectRepository
import es.uniovi.apuntesuniovi.repositories.UserRepository
import es.uniovi.apuntesuniovi.services.commands.subjects.FindSubjectById
import es.uniovi.apuntesuniovi.services.commands.users.FindUserById
import org.springframework.data.domain.Pageable
import org.springframework.util.Assert

/**
 * Create a LearnSubject in service layer
 */
class CreateLearnSubject(
    private val learnSubjectRepository: LearnSubjectRepository,
    private val userRepository: UserRepository,
    private val subjectRepository: SubjectRepository,
    private val id: Long,
    private val learnSubjects: List<LearnSubject>
) : AbstractCommand<List<LearnSubject>>() {

    override fun execute(): List<LearnSubject> {
        logService.info("execute() - start")
        checkData()
        checkExists()
        val result = learnSubjectRepository.saveAll(learnSubjects)
        logService.info("execute() - end")
        return result.toList()
    }

    private fun checkExists() {
        logService.info("checkExists() - start")
        val list = learnSubjectRepository.findBySubjectId(id, Pageable.unpaged())
        if (list.content.isNotEmpty()) {
            list.forEach { x -> learnSubjectRepository.deleteById(x.id!!) }
        }
        logService.info("checkExists() - end")
    }

    private fun checkData() {
        logService.info("checkData() - start")
        Assert.isTrue(
            FindSubjectById(subjectRepository, id).execute().id != null,
            LearnSubjectMessages.INVALID_CREATE_DATA
        )
        learnSubjects.forEach { learnSubject ->
            learnSubject.student = FindUserById(userRepository, learnSubject.student.id!!).execute()
            Assert.isTrue(
                learnSubject.student.role == RoleType.ROLE_STUDENT,
                LearnSubjectMessages.INVALID_USER_ROLE
            )
        }
        logService.info("checkData() - start")
    }


}