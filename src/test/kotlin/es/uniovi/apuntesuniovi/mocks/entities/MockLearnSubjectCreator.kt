package es.uniovi.apuntesuniovi.mocks.entities

import es.uniovi.apuntesuniovi.mocks.MockCreator
import es.uniovi.apuntesuniovi.models.LearnSubject

/**
 * Service to create mock data of the entity LearnSubject
 */
class MockLearnSubjectCreator : MockCreator<LearnSubject> {

    override fun create(): LearnSubject {
        val learnSubject = LearnSubject()
        learnSubject.id = 1
        learnSubject.subject = MockSubjectCreator().create()
        learnSubject.student = MockUserCreator().createStudent()
        return learnSubject
    }
}