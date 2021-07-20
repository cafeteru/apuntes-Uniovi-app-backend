package es.uniovi.apuntesuniovi.services.commands.teachSubjects

import es.uniovi.apuntesuniovi.infrastructure.AbstractCommand
import es.uniovi.apuntesuniovi.infrastructure.messages.TeachSubjectMessages
import es.uniovi.apuntesuniovi.models.TeachSubject
import es.uniovi.apuntesuniovi.models.types.RoleType
import es.uniovi.apuntesuniovi.repositories.SubjectRepository
import es.uniovi.apuntesuniovi.repositories.TeachSubjectRepository
import es.uniovi.apuntesuniovi.repositories.UserRepository
import es.uniovi.apuntesuniovi.services.commands.subjects.FindSubjectById
import es.uniovi.apuntesuniovi.services.commands.users.FindUserById
import org.springframework.util.Assert

/**
 * Create a TeachSubject in service layer
 */
class CreateTeachSubject(
    private val teachSubjectRepository: TeachSubjectRepository,
    private val userRepository: UserRepository,
    private val subjectRepository: SubjectRepository,
    private val id: Long,
    private val teachSubjects: List<TeachSubject>
) : AbstractCommand<List<TeachSubject>>() {

    override fun execute(): List<TeachSubject> {
        logService.info("execute() - start")
        checkData()
        checkExists()
        val result = teachSubjectRepository.saveAll(teachSubjects)
        logService.info("execute() - end")
        return result.toList()
    }

    private fun checkExists() {
        logService.info("checkExists() - start")
        val list = teachSubjectRepository.findBySubjectId(id)
        if (list.isNotEmpty()) {
            list.forEach { x -> teachSubjectRepository.deleteById(x.id!!) }
        }
        logService.info("checkExists() - end")
    }

    private fun checkData() {
        logService.info("checkData() - start")
        Assert.isTrue(
            FindSubjectById(subjectRepository, id).execute().id != null,
            TeachSubjectMessages.INVALID_CREATE_DATA
        )
        teachSubjects.forEach { learnSubject ->
            learnSubject.teacher = FindUserById(userRepository, learnSubject.teacher.id!!).execute()
            Assert.isTrue(
                learnSubject.teacher.role == RoleType.ROLE_TEACHER,
                TeachSubjectMessages.INVALID_USER_ROLE
            )
        }
        logService.info("checkData() - start")
    }


}