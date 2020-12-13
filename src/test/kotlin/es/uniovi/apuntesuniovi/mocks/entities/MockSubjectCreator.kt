package es.uniovi.apuntesuniovi.mocks.entities

import es.uniovi.apuntesuniovi.mocks.MockCreator
import es.uniovi.apuntesuniovi.models.Subject
import es.uniovi.apuntesuniovi.models.types.SubjectType

/**
 * Service to create mock data of the entity Subject
 */
class MockSubjectCreator : MockCreator<Subject> {
    override fun create(): Subject {
        val subject = Subject()
        subject.id = 1
        subject.name = "subject"
        subject.subjectType = SubjectType.BASIC
        subject.semester = MockSemesterCreator().create()
        return subject
    }
}