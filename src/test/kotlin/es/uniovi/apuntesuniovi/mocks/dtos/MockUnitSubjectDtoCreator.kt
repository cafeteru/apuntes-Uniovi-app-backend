package es.uniovi.apuntesuniovi.mocks.dtos

import es.uniovi.apuntesuniovi.infrastructure.constants.database.UnitSubjectLimits
import es.uniovi.apuntesuniovi.mocks.MockCreator
import es.uniovi.apuntesuniovi.mocks.entities.MockSubjectCreator
import es.uniovi.apuntesuniovi.services.dtos.entities.UnitSubjectDto

/**
 * Service to create mock data of the dto UnitSubject
 */
class MockUnitSubjectDtoCreator : MockCreator<UnitSubjectDto> {
  override fun create(): UnitSubjectDto {
    return UnitSubjectDto(
      id = 3,
      name = "unitSubject",
      position = UnitSubjectLimits.POSITION_MIN,
      subjectId = MockSubjectCreator().create().id
    )
  }
}