package es.uniovi.apuntesuniovi.servicies.commands.subjects

import es.uniovi.apuntesuniovi.entities.Subject
import es.uniovi.apuntesuniovi.entities.TeachSubject
import es.uniovi.apuntesuniovi.entities.TeachSubjectRegistry
import es.uniovi.apuntesuniovi.entities.User
import es.uniovi.apuntesuniovi.entities.types.RoleType
import es.uniovi.apuntesuniovi.infrastructure.Command
import es.uniovi.apuntesuniovi.infrastructure.exceptions.messages.SubjectMessages
import es.uniovi.apuntesuniovi.infrastructure.exceptions.messages.UserMessages
import es.uniovi.apuntesuniovi.infrastructure.log.LogService
import es.uniovi.apuntesuniovi.repositories.SubjectRepository
import es.uniovi.apuntesuniovi.repositories.TeachSubjectRegistryRepository
import es.uniovi.apuntesuniovi.repositories.TeachSubjectRepository
import es.uniovi.apuntesuniovi.repositories.UserRepository
import es.uniovi.apuntesuniovi.servicies.dtos.entities.TeachSubjectDto
import es.uniovi.apuntesuniovi.servicies.dtos.impl.TeachSubjectDtoAssembler
import java.time.LocalDate

/**
 * Add teacher to a subject in service layer
 */
class AddTeacherService(
    private val subjectRepository: SubjectRepository,
    private val userRepository: UserRepository,
    private val teachSubjectRepository: TeachSubjectRepository,
    private val teachSubjectRegistryRepository: TeachSubjectRegistryRepository,
    private val teachSubjectDtoAssembler: TeachSubjectDtoAssembler,
    private val subjectId: Long,
    private val teacherId: Long,
    private val date: LocalDate
) : Command<List<TeachSubjectDto>> {
    private val logService = LogService(this.javaClass)

    override fun execute(): List<TeachSubjectDto> {
        logService.info("execute() - start")
        var teachSubject = TeachSubject()
        teachSubject.subject = getSubject()
        teachSubject.teacher = getTeacher()
        teachSubject = teachSubjectRepository.save(teachSubject)
        val teachSubjectRegistry = TeachSubjectRegistry()
        teachSubjectRegistry.teachSubject = teachSubject
        teachSubjectRegistry.initDay = date
        teachSubjectRegistryRepository.save(teachSubjectRegistry)
        val list = ArrayList<TeachSubject>()
        list.add(teachSubject)
        logService.info("execute() - end")
        return teachSubjectDtoAssembler.listToDto(list)
    }

    private fun getSubject(): Subject {
        val optional = subjectRepository.findById(subjectId)
        if (optional.isEmpty) {
            throw IllegalArgumentException(SubjectMessages.NOT_EXISTS)
        }
        return optional.get()
    }

    private fun getTeacher(): User {
        val optional = userRepository.findById(teacherId)
        if (optional.isEmpty || optional.get().role != RoleType.TEACHER) {
            throw IllegalArgumentException(UserMessages.NOT_EXISTS)
        }
        return optional.get()
    }
}