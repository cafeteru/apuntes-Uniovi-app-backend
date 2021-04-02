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
        val name = unitSubject.name!!
        val subjectId = unitSubject.subject!!.id!!
        val position = unitSubject.position!!
        Assert.isTrue(unitSubjectRepository.existsByNameAndSubjectId(name, subjectId), UnitSubjectMessages.EXISTS_NAME)
        Assert.isTrue(
            unitSubjectRepository.existsByPositionAndSubjectId(position, subjectId),
            UnitSubjectMessages.EXISTS_POSITION
        )
        logService.info("checkData() - end")
    }
}