package es.uniovi.apuntesuniovi.services.dtos

import es.uniovi.apuntesuniovi.infrastructure.messages.CourseMessages
import es.uniovi.apuntesuniovi.mocks.dtos.MockCourseDtoCreator
import es.uniovi.apuntesuniovi.mocks.entities.MockCareerCreator
import es.uniovi.apuntesuniovi.mocks.entities.MockCourseCreator
import es.uniovi.apuntesuniovi.repositories.CareerRepository
import es.uniovi.apuntesuniovi.services.dtos.assemblers.CourseAssembler
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.fail
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*
import kotlin.test.assertNull

/**
 * Check class CourseAssembler
 */
@ExtendWith(MockitoExtension::class)
class CourseAssemblerTest {
  @Mock
  private lateinit var careerRepository: CareerRepository

  private lateinit var courseAssembler: CourseAssembler

  /**
   * Create init data for the test
   */
  @BeforeEach
  fun initTest() {
    courseAssembler = CourseAssembler(careerRepository)
  }

  /**
   * Checks the conversion with valid Course
   */
  @Test
  fun validCourse() {
    val course = MockCourseCreator().create()
    val dto = courseAssembler.entityToDto(course)
    assertEquals(course.id, dto.id)
    assertEquals(course.position, dto.position)
    assertEquals(course.career?.id, dto.careerId)
  }

  /**
   * Checks the conversion with valid Course without Career
   */
  @Test
  fun validCourseNullCareer() {
    val course = MockCourseCreator().create()
    course.career = null
    val dto = courseAssembler.entityToDto(course)
    assertEquals(course.id, dto.id)
    assertEquals(course.position, dto.position)
    assertEquals(course.career?.id, dto.careerId)
  }

  /**
   * Checks the conversion with null Course
   */
  @Test
  fun nullCourse() {
    try {
      courseAssembler.entityToDto(null)
      fail("Course can´t be null")
    } catch (e: IllegalArgumentException) {
      assertEquals(e.message, CourseMessages.NULL)
    }
  }

  /**
   * Checks the conversion with valid CareerDto
   */
  @Test
  fun validCourseDto() {
    val dto = MockCourseDtoCreator().create()
    Mockito.`when`(careerRepository.findById(dto.careerId!!)).thenReturn(
      Optional.of(MockCareerCreator().create())
    )
    val course = courseAssembler.dtoToEntity(dto)
    assertEquals(course.id, dto.id)
    assertEquals(course.position, dto.position)
    assertEquals(course.career?.id, dto.careerId)
  }

  /**
   * Checks the conversion with valid CareerDto without careerId
   */
  @Test
  fun validCourseDtoNullCareerId() {
    val dto = MockCourseDtoCreator().create()
    dto.careerId = null
    val course = courseAssembler.dtoToEntity(dto)
    assertEquals(course.id, dto.id)
    assertEquals(course.position, dto.position)
    assertNull(course.career)
    assertNull(dto.careerId)
  }

  /**
   * Checks the conversion with null CareerDto
   */
  @Test
  fun nullCourseDto() {
    try {
      courseAssembler.dtoToEntity(null)
      fail("CourseDto can´t be null")
    } catch (e: IllegalArgumentException) {
      assertEquals(e.message, CourseMessages.NULL)
    }
  }
}