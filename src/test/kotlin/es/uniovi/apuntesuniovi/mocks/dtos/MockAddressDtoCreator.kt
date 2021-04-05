package es.uniovi.apuntesuniovi.mocks.dtos

import es.uniovi.apuntesuniovi.dtos.entities.AddressDto
import es.uniovi.apuntesuniovi.mocks.MockCreator

/**
 * Service to create mock data of the entity Address
 */
class MockAddressDtoCreator : MockCreator<AddressDto> {
    override fun create(): AddressDto {
        return AddressDto(
            id = 1,
            street = "street",
            city = "city",
            country = "country",
            postalCode = "postalCode"
        )
    }
}