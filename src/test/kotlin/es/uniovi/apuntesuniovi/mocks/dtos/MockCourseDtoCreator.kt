package es.uniovi.apuntesuniovi.mocks.dtos

import es.uniovi.apuntesuniovi.infrastructure.constants.database.CourseLimits
import es.uniovi.apuntesuniovi.mocks.MockCreator
import es.uniovi.apuntesuniovi.mocks.entities.MockCareerCreator
import es.uniovi.apuntesuniovi.services.dtos.entities.CourseDto

/**
 * Service to create mock data of the dto CourseDto
 */
class MockCourseDtoCreator : MockCreator<CourseDto> {
  override fun create(): CourseDto {
    return CourseDto(
      id = 3,
      position = CourseLimits.POSITION_MIN,
      careerId = MockCareerCreator().create().id
    )
  }
}