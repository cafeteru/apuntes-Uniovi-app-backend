package es.uniovi.apuntesuniovi.mocks.entities

import es.uniovi.apuntesuniovi.mocks.MockCreator
import es.uniovi.apuntesuniovi.models.UnitSubject

/**
 * Service to create mock data of the entity UnitSubject
 */
class MockUnitSubject : MockCreator<UnitSubject> {
    override fun create(): UnitSubject {
        val unitSubject = UnitSubject()
        unitSubject.id = 1
        unitSubject.name = "unitSubject"
        unitSubject.subject = MockSubject().create()
        return unitSubject
    }
}