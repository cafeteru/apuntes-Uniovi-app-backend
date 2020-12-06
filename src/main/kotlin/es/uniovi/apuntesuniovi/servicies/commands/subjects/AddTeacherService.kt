package es.uniovi.apuntesuniovi.servicies.commands.subjects

import es.uniovi.apuntesuniovi.entities.Subject
import es.uniovi.apuntesuniovi.entities.TeachSubject
import es.uniovi.apuntesuniovi.entities.User
import es.uniovi.apuntesuniovi.entities.types.RoleType
import es.uniovi.apuntesuniovi.infrastructure.AbstractCommand
import es.uniovi.apuntesuniovi.infrastructure.exceptions.messages.UserMessages
import es.uniovi.apuntesuniovi.repositories.TeachSubjectRepository

/**
 * Add teacher to a subject in service layer
 */
class AddTeacherService(
    private val teachSubjectRepository: TeachSubjectRepository,
    private val subject: Subject,
    private val teacher: User
) : AbstractCommand<List<TeachSubject>>() {
    override fun execute(): List<TeachSubject> {
        logService.info("execute() - start")
        var teachSubject = TeachSubject()
        teachSubject.subject = subject
        teachSubject.teacher = getTeacher()
        teachSubject = teachSubjectRepository.save(teachSubject)
        logService.info("execute() - end")
        return listOf(teachSubject)
    }

    private fun getTeacher(): User {
        if (teacher.role != RoleType.TEACHER) {
            throw IllegalArgumentException(UserMessages.NOT_EXISTS)
        }
        return teacher
    }
}