package es.uniovi.apuntesuniovi.services.commands.teachSubjects

import es.uniovi.apuntesuniovi.infrastructure.messages.TeachSubjectMessages
import es.uniovi.apuntesuniovi.models.TeachSubject
import es.uniovi.apuntesuniovi.repositories.TeachSubjectRepository
import es.uniovi.apuntesuniovi.services.commands.BaseCreateCommand
import org.springframework.util.Assert

/**
 * Create a TeachSubject in service layer
 */
class CreateTeachSubject(
    private val teachSubjectRepository: TeachSubjectRepository,
    private val teachSubject: TeachSubject
) : BaseCreateCommand<TeachSubject>(teachSubjectRepository, teachSubject) {

    override fun checkData() {
        logService.info("checkData() - start")
        val teacherId = teachSubject.teacher.id
        val subjectId = teachSubject.subject.id
        Assert.isTrue(teacherId == null || subjectId == null, TeachSubjectMessages.INVALID_CREATE_DATA)
        Assert.isTrue(
            teachSubjectRepository.existsBySubjectIdAndTeacherId(subjectId!!, teacherId!!),
            TeachSubjectMessages.INVALID_CREATE_DATA
        )
        logService.info("checkData() - end")
    }
}