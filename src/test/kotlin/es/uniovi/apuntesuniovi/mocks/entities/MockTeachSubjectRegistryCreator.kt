package es.uniovi.apuntesuniovi.mocks.entities

import es.uniovi.apuntesuniovi.mocks.MockCreator
import es.uniovi.apuntesuniovi.models.TeachSubjectRegistry
import java.time.LocalDate

/**
 * Service to create mock data of the entity TeachSubjectRegistry
 */
class MockTeachSubjectRegistryCreator : MockCreator<TeachSubjectRegistry> {
    override fun create(): TeachSubjectRegistry {
        val registry = TeachSubjectRegistry()
        registry.id = 1
        registry.teachSubject = MockTeachSubjectCreator().create()
        registry.initDay = LocalDate.now()
        return registry
    }
}