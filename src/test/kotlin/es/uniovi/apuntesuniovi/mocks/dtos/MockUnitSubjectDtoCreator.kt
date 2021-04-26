package es.uniovi.apuntesuniovi.mocks.dtos

import es.uniovi.apuntesuniovi.dtos.entities.UnitSubjectDto
import es.uniovi.apuntesuniovi.mocks.MockCreator
import es.uniovi.apuntesuniovi.mocks.entities.MockSubjectCreator

/**
 * Service to create mock data of the dto UnitSubject
 */
class MockUnitSubjectDtoCreator : MockCreator<UnitSubjectDto> {
    override fun create(): UnitSubjectDto {
        return UnitSubjectDto(
            id = 3,
            name = "unitSubject",
            position = 1,
            subjectId = MockSubjectCreator().create().id
        )
    }
}