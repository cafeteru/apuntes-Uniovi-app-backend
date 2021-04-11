package es.uniovi.apuntesuniovi.services.commands.learnSubjects

import es.uniovi.apuntesuniovi.infrastructure.AbstractCommand
import es.uniovi.apuntesuniovi.infrastructure.messages.LearnSubjectMessages
import es.uniovi.apuntesuniovi.models.LearnSubject
import es.uniovi.apuntesuniovi.models.types.RoleType
import es.uniovi.apuntesuniovi.repositories.LearnSubjectRepository
import org.springframework.util.Assert

/**
 * Create a LearnSubject in service layer
 */
class CreateLearnSubject(
    private val learnSubjectRepository: LearnSubjectRepository,
    private val id: Long,
    private val learnSubjects: List<LearnSubject>
) : AbstractCommand<MutableIterable<LearnSubject>>() {

    override fun execute(): MutableIterable<LearnSubject> {
        logService.info("execute() - start")
        checkData()
        checkExists()
        val result = learnSubjectRepository.saveAll(learnSubjects)
        logService.info("execute() - end")
        return result
    }

    private fun checkExists() {
        logService.info("checkExists() - start")
        val list = learnSubjectRepository.findBySubjectId(id)
        if (list.isNotEmpty()) {
            list.forEach { x -> learnSubjectRepository.deleteById(x.id!!) }
        }
        logService.info("checkExists() - end")
    }

    private fun checkData() {
        logService.info("checkData() - start")
        learnSubjects.forEach { learnSubject ->
            Assert.isTrue(
                learnSubject.student.role == RoleType.STUDENT,
                LearnSubjectMessages.INVALID_CREATE_DATA
            )
        }
        logService.info("checkData() - start")
    }


}