package es.uniovi.apuntesuniovi.mocks.entities

import es.uniovi.apuntesuniovi.mocks.MockCreator
import es.uniovi.apuntesuniovi.models.Semester

/**
 * Service to create mock data of the entity Semester
 */
class MockSemesterCreator : MockCreator<Semester> {
    override fun create(): Semester {
        val semester = Semester()
        semester.id = 1
        semester.position = 1
        semester.course = MockCourseCreator().create()
        return semester
    }
}