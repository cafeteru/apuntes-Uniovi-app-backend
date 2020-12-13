package es.uniovi.apuntesuniovi.services.commands.teachSubjects

import es.uniovi.apuntesuniovi.infrastructure.messages.TeachSubjectMessages
import es.uniovi.apuntesuniovi.models.TeachSubject
import es.uniovi.apuntesuniovi.repositories.TeachSubjectRepository
import es.uniovi.apuntesuniovi.services.commands.BaseCreateService

/**
 * Create a TeachSubject in service layer
 */
class CreateTeachSubjectService(
    private val teachSubjectRepository: TeachSubjectRepository,
    private val teachSubject: TeachSubject
) : BaseCreateService<TeachSubject>(teachSubjectRepository, teachSubject) {
    override fun execute(): TeachSubject {
        val teacherId = teachSubject.teacher.id
        val subjectId = teachSubject.subject.id
        if (teacherId != null && subjectId != null) {
            if (teachSubjectRepository.findBySubjectIdAndTeacherId(subjectId, teacherId).isPresent) {
                throw IllegalArgumentException(TeachSubjectMessages.ALREADY_CREATE)
            }
            return super.execute()
        }
        throw IllegalArgumentException(TeachSubjectMessages.INVALID_CREATE_DATA)
    }
}