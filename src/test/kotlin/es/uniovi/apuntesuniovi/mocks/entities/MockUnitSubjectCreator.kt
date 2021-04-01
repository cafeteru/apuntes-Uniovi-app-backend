package es.uniovi.apuntesuniovi.mocks.entities

import es.uniovi.apuntesuniovi.mocks.MockCreator
import es.uniovi.apuntesuniovi.models.UnitSubject

/**
 * Service to create mock data of the entity UnitSubject
 */
class MockUnitSubjectCreator : MockCreator<UnitSubject> {
    override fun create(): UnitSubject {
        val unitSubject = UnitSubject()
        unitSubject.id = 1
        unitSubject.name = "unitSubject"
        unitSubject.position = 1
        unitSubject.subject = MockSubjectCreator().create()
        return unitSubject
    }
}