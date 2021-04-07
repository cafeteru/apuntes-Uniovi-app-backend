package es.uniovi.apuntesuniovi.services.commands.teachSubjects

import es.uniovi.apuntesuniovi.infrastructure.messages.TeachSubjectMessages
import es.uniovi.apuntesuniovi.models.TeachSubject
import es.uniovi.apuntesuniovi.models.types.RoleType
import es.uniovi.apuntesuniovi.repositories.TeachSubjectRepository
import es.uniovi.apuntesuniovi.services.commands.BaseUpdateAll
import org.springframework.util.Assert

/**
 * Create a TeachSubject in service layer
 */
class UpdateTeachSubject(
    private val teachSubjectRepository: TeachSubjectRepository,
    private val id: Long,
    private val teachSubjects: List<TeachSubject>
) : BaseUpdateAll<TeachSubject>(teachSubjectRepository, teachSubjects) {

    override fun getListEntity(): List<TeachSubject> {
        return teachSubjectRepository.findBySubjectId(id)
    }

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