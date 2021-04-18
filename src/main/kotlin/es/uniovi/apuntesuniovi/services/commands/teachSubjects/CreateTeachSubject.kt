package es.uniovi.apuntesuniovi.services.commands.teachSubjects

import es.uniovi.apuntesuniovi.infrastructure.AbstractCommand
import es.uniovi.apuntesuniovi.infrastructure.messages.TeachSubjectMessages
import es.uniovi.apuntesuniovi.models.TeachSubject
import es.uniovi.apuntesuniovi.models.types.RoleType
import es.uniovi.apuntesuniovi.repositories.TeachSubjectRepository
import org.springframework.util.Assert

/**
 * Create a TeachSubject in service layer
 */
class CreateTeachSubject(
    private val teachSubjectRepository: TeachSubjectRepository,
    private val id: Long,
    private val teachSubjects: List<TeachSubject>
) : AbstractCommand<MutableIterable<TeachSubject>>() {

    override fun execute(): MutableIterable<TeachSubject> {
        logService.info("execute() - start")
        checkData()
        checkExists()
        val result = teachSubjectRepository.saveAll(teachSubjects)
        logService.info("execute() - end")
        return result
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
        teachSubjects.forEach { teachSubject ->
            Assert.isTrue(
                teachSubject.teacher.role == RoleType.ROLE_TEACHER,
                TeachSubjectMessages.INVALID_CREATE_DATA
            )
        }
        logService.info("checkData() - start")
    }


}