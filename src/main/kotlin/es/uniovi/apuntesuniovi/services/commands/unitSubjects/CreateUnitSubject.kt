package es.uniovi.apuntesuniovi.services.commands.unitSubjects

import es.uniovi.apuntesuniovi.infrastructure.messages.UnitSubjectMessages
import es.uniovi.apuntesuniovi.models.UnitSubject
import es.uniovi.apuntesuniovi.repositories.UnitSubjectRepository
import es.uniovi.apuntesuniovi.services.commands.BaseCreate
import io.jsonwebtoken.lang.Assert

/**
 * Create a UnitSubject in service layer
 */
class CreateUnitSubject(
    private val unitSubjectRepository: UnitSubjectRepository,
    private val unitSubject: UnitSubject
) : BaseCreate<UnitSubject>(unitSubjectRepository, unitSubject) {

    override fun checkData() {
        logService.info("checkData() - start")
        val name = unitSubject.name ?: throw IllegalArgumentException(UnitSubjectMessages.NULL_NAME)
        val subjectId = unitSubject.subject!!.id ?: throw IllegalArgumentException(UnitSubjectMessages.INVALID_SUBJECT_ID)
        Assert.isTrue(
            !unitSubjectRepository.existsByNameAndSubjectId(name, subjectId),
            UnitSubjectMessages.EXISTS_NAME
        )
        Assert.isTrue(
            !unitSubjectRepository.existsByPositionAndSubjectId(unitSubject.position, subjectId),
            UnitSubjectMessages.EXISTS_POSITION
        )
        logService.info("checkData() - end")
    }
}