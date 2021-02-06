package es.uniovi.apuntesuniovi.mocks.entities

import es.uniovi.apuntesuniovi.infrastructure.constants.database.OptionETCSLimits
import es.uniovi.apuntesuniovi.mocks.MockCreator
import es.uniovi.apuntesuniovi.models.OptionETCS
import es.uniovi.apuntesuniovi.models.types.SubjectType

/**
 * Service to create mock data of the entity OptionETCS
 */
class MockOptionETCSCreator : MockCreator<OptionETCS> {
  override fun create(): OptionETCS {
    val optionETCS = OptionETCS()
    optionETCS.id = 1
    optionETCS.etcs = OptionETCSLimits.ECTS_MIN
    optionETCS.subjectType = SubjectType.BASIC
    return optionETCS
  }
}