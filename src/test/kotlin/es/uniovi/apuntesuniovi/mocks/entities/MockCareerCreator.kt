package es.uniovi.apuntesuniovi.mocks.entities

import es.uniovi.apuntesuniovi.mocks.MockCreator
import es.uniovi.apuntesuniovi.models.Career
import es.uniovi.apuntesuniovi.models.types.LanguageType

/**
 * Service to create mock data of the entity Career
 */
class MockCareerCreator : MockCreator<Career> {
  override fun create(): Career {
    val career = Career()
    career.id = 1
    career.name = "CenterTest"
    career.code = "TEST"
    career.yearImplantation = 2020
    career.addLanguage(LanguageType.ES.toString())
    career.addLanguage(LanguageType.US.toString())
    return career
  }
}