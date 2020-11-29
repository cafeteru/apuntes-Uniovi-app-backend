package es.uniovi.apuntesuniovi.servicies.impl.subjects

import es.uniovi.apuntesuniovi.entities.TeachSubject
import es.uniovi.apuntesuniovi.entities.types.RoleType
import es.uniovi.apuntesuniovi.infrastructure.Command
import es.uniovi.apuntesuniovi.log.LogService
import es.uniovi.apuntesuniovi.repositories.SubjectRepository
import es.uniovi.apuntesuniovi.repositories.TeachSubjectRepository
import es.uniovi.apuntesuniovi.repositories.UserRepository
import es.uniovi.apuntesuniovi.servicies.dtos.entities.UserDto
import es.uniovi.apuntesuniovi.servicies.dtos.impl.UserDtoAssembler

class AddTeacherService(
        private val subjectRepository: SubjectRepository,
        private val userRepository: UserRepository,
        private val teachSubjectRepository: TeachSubjectRepository,
        private val subjectId: Long,
        private val teacherId: Long,
) : Command<List<UserDto>> {
    private val logService = LogService(this.javaClass)

    // TODO Change type return
    override fun execute(): List<UserDto> {
        logService.info("execute() - start")
        // TODO Extract searches to two methods
        val optionalSubject = subjectRepository.findById(subjectId)
        val optionalTeacher = userRepository.findById(teacherId)
        if (optionalSubject.isPresent && optionalTeacher.isPresent) {
            val teacher = optionalTeacher.get()
            if (teacher.role == RoleType.TEACHER) {
                val teachSubject = TeachSubject()
                teachSubject.subject = optionalSubject.get()
                teachSubject.teacher = optionalTeacher.get()
                teachSubjectRepository.save(teachSubject)
                // TODO ADD teachSubjectRegistry
                logService.info("execute() - end")
                return ArrayList()
            }
            logService.error("execute() - error")
            // TODO Create exception message
            throw IllegalArgumentException("")
        }
        logService.error("execute() - error")
        // TODO Create exception message
        throw IllegalArgumentException("")
    }
}