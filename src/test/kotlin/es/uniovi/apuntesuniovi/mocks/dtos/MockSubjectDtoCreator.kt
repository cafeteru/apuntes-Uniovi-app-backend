package es.uniovi.apuntesuniovi.mocks.dtos

import es.uniovi.apuntesuniovi.mocks.MockCreator
import es.uniovi.apuntesuniovi.models.types.SubjectType
import es.uniovi.apuntesuniovi.dtos.entities.SubjectDto

/**
 * Service to create mock data of the dto SubjectDto
 */
class MockSubjectDtoCreator : MockCreator<SubjectDto> {
  override fun create(): SubjectDto {
    return SubjectDto(
      id = 3,
      name = "name",
      subjectType = SubjectType.BASIC.toString(),
      active = true
    )
  }
}