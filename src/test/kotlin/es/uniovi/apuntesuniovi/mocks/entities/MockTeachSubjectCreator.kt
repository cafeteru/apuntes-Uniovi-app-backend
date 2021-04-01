package es.uniovi.apuntesuniovi.mocks.entities

import es.uniovi.apuntesuniovi.mocks.MockCreator
import es.uniovi.apuntesuniovi.models.TeachSubject

/**
 * Service to create mock data of the entity TeachSubject
 */
class MockTeachSubjectCreator : MockCreator<TeachSubject> {
    override fun create(): TeachSubject {
        val teachSubject = TeachSubject()
        teachSubject.id = 1
        teachSubject.isCoordinator = true
        teachSubject.subject = MockSubjectCreator().create()
        teachSubject.teacher = MockUserCreator().createTeacher()
        return teachSubject
    }
}