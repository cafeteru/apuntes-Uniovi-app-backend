package es.uniovi.apuntesuniovi.services.commands.unitSubjects

import es.uniovi.apuntesuniovi.infrastructure.messages.UnitSubjectMessages
import es.uniovi.apuntesuniovi.models.UnitSubject
import es.uniovi.apuntesuniovi.repositories.UnitSubjectRepository
import es.uniovi.apuntesuniovi.services.commands.BaseCreateService

/**
 * Create a UnitSubject in service layer
 */
class CreateUnitSubjectService(
    private val unitSubjectRepository: UnitSubjectRepository,
    private val unitSubject: UnitSubject
) : BaseCreateService<UnitSubject>(unitSubjectRepository, unitSubject) {
    override fun execute(): UnitSubject {
        val name = unitSubject.name!!
        val subjectId = unitSubject.subject!!.id!!
        if (unitSubjectRepository.existsByNameAndSubjectId(name, subjectId)) {
            throw IllegalArgumentException(UnitSubjectMessages.EXISTS_NAME)
        }
        val position = unitSubject.position!!
        if (unitSubjectRepository.existsByPositionAndSubjectId(position, subjectId)) {
            throw IllegalArgumentException(UnitSubjectMessages.EXISTS_POSITION)
        }
        return super.execute()
    }
}