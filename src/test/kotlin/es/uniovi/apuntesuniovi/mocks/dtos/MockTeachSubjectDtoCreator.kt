package es.uniovi.apuntesuniovi.mocks.dtos

import es.uniovi.apuntesuniovi.mocks.MockCreator
import es.uniovi.apuntesuniovi.mocks.entities.MockSubjectCreator
import es.uniovi.apuntesuniovi.mocks.entities.MockUserCreator
import es.uniovi.apuntesuniovi.services.dtos.entities.TeachSubjectDto

/**
 * Service to create mock data of the dto TeachSubjectDto
 */
class MockTeachSubjectDtoCreator : MockCreator<TeachSubjectDto> {
    override fun create(): TeachSubjectDto {
        return TeachSubjectDto(
            id = 3,
            isCoordinator = true,
            subjectId = MockSubjectCreator().create().id!!,
            teacherId = MockUserCreator().createTeacher().id!!
        )
    }
}