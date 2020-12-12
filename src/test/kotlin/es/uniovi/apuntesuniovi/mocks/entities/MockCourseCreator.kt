package es.uniovi.apuntesuniovi.mocks.entities

import es.uniovi.apuntesuniovi.mocks.MockCreator
import es.uniovi.apuntesuniovi.models.Course

/**
 * Service to create mock data of the entity Course
 */
class MockCourseCreator : MockCreator<Course> {
    override fun create(): Course {
        val course = Course()
        course.id = 1
        course.position = 1
        course.career = MockCareerCreator().create()
        return course
    }
}