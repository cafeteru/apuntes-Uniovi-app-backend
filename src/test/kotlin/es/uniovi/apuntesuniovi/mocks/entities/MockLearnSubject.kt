package es.uniovi.apuntesuniovi.mocks.entities

import es.uniovi.apuntesuniovi.mocks.MockCreator
import es.uniovi.apuntesuniovi.models.LearnSubject

/**
 * Service to create mock data of the entity LearnSubject
 */
class MockLearnSubject : MockCreator<LearnSubject> {

    override fun create(): LearnSubject {
        val learnSubject = LearnSubject()
        learnSubject.id = 1
        learnSubject.subject = MockSubject().create()
        learnSubject.student = MockUser().createStudent()
        return learnSubject
    }
}