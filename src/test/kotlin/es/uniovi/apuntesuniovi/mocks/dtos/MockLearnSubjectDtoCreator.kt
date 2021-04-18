package es.uniovi.apuntesuniovi.mocks.dtos

import es.uniovi.apuntesuniovi.dtos.entities.LearnSubjectDto
import es.uniovi.apuntesuniovi.mocks.MockCreator
import es.uniovi.apuntesuniovi.mocks.entities.MockSubjectCreator
import es.uniovi.apuntesuniovi.mocks.entities.MockUserCreator

/**
 * Service to create mock data of the dto LearnSubjectDto
 */
class MockLearnSubjectDtoCreator : MockCreator<LearnSubjectDto> {

    override fun create(): LearnSubjectDto {
        return LearnSubjectDto(
            id = 3,
            subjectId = MockSubjectCreator().create().id!!,
            studentId = MockUserCreator().createStudent().id!!
        )
    }
}