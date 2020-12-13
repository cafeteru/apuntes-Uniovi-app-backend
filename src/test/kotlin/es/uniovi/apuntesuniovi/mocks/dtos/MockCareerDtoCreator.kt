package es.uniovi.apuntesuniovi.mocks.dtos

import es.uniovi.apuntesuniovi.infrastructure.constants.database.CareerLimits
import es.uniovi.apuntesuniovi.mocks.MockCreator
import es.uniovi.apuntesuniovi.mocks.entities.MockCenterCreator
import es.uniovi.apuntesuniovi.models.types.LanguageType
import es.uniovi.apuntesuniovi.services.dtos.entities.CareerDto

/**
 * Service to create mock data of the dto CareerDto
 */
class MockCareerDtoCreator : MockCreator<CareerDto> {
    override fun create(): CareerDto {
        return CareerDto(
            id = 3,
            name = "name",
            code = "code",
            yearImplantation = 1990,
            etcs = CareerLimits.ECTS_MIN,
            centerId = MockCenterCreator().create().id,
            languages = listOf(LanguageType.SPANISH.toString(), LanguageType.ENGLISH.toString())
        )
    }
}