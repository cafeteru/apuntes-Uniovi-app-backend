package es.uniovi.apuntesuniovi.services.commands.teachSubjects

import es.uniovi.apuntesuniovi.infrastructure.messages.TeachSubjectMessages
import es.uniovi.apuntesuniovi.models.TeachSubject
import es.uniovi.apuntesuniovi.models.types.RoleType
import es.uniovi.apuntesuniovi.repositories.TeachSubjectRepository
import es.uniovi.apuntesuniovi.services.commands.BaseCreateAll
import org.springframework.util.Assert

/**
 * Create a TeachSubject in service layer
 */
class CreateTeachSubject(
    teachSubjectRepository: TeachSubjectRepository,
    private val teachSubjects: List<TeachSubject>
) : BaseCreateAll<TeachSubject>(teachSubjectRepository, teachSubjects) {

    override fun checkData() {
        logService.info("checkData() - start")
        teachSubjects.forEach { teachSubject ->
            Assert.isTrue(
                teachSubject.teacher.role == RoleType.TEACHER,
                TeachSubjectMessages.INVALID_CREATE_DATA
            )
        }
        logService.info("checkData() - start")
    }


}