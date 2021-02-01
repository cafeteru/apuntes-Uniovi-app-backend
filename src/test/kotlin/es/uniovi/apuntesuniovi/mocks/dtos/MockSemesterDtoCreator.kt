package es.uniovi.apuntesuniovi.mocks.dtos

import es.uniovi.apuntesuniovi.mocks.MockCreator
import es.uniovi.apuntesuniovi.mocks.entities.MockCourseCreator
import es.uniovi.apuntesuniovi.services.dtos.entities.SemesterDto

/**
 * Service to create mock data of the dto SemesterDto
 */
class MockSemesterDtoCreator : MockCreator<SemesterDto> {
  override fun create(): SemesterDto {
    return SemesterDto(
      id = 3,
      position = 1,
      courseId = MockCourseCreator().create().id
    )
  }
}