package es.uniovi.apuntesuniovi.mocks.dtos

import es.uniovi.apuntesuniovi.mocks.MockCreator
import es.uniovi.apuntesuniovi.mocks.entities.MockTeachSubjectCreator
import es.uniovi.apuntesuniovi.services.dtos.entities.TeachSubjectRegistryDto
import java.time.LocalDate

/**
 * Service to create mock data of the dto TeachSubjectRegistryDto
 */
class MockTeachSubjectRegistryDtoCreator : MockCreator<TeachSubjectRegistryDto> {
    override fun create(): TeachSubjectRegistryDto {
        return TeachSubjectRegistryDto(
            id = 3,
            teachSubjectId = MockTeachSubjectCreator().create().id,
            initDay = LocalDate.now(),
            finishDay = null
        )
    }
}