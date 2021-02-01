package es.uniovi.apuntesuniovi.mocks.dtos

import es.uniovi.apuntesuniovi.mocks.MockCreator
import es.uniovi.apuntesuniovi.mocks.entities.MockAddressCreator
import es.uniovi.apuntesuniovi.services.dtos.entities.CenterDto

/**
 * Service to create mock data of the dto CenterDto
 */
class MockCenterDtoCreator : MockCreator<CenterDto> {
  override fun create(): CenterDto {
    return CenterDto(
      id = 3,
      name = "name",
      address = MockAddressCreator().create()
    )
  }
}