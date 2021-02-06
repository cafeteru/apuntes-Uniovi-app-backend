package es.uniovi.apuntesuniovi.services.dtos

import es.uniovi.apuntesuniovi.infrastructure.messages.SemesterMessages
import es.uniovi.apuntesuniovi.mocks.dtos.MockSemesterDtoCreator
import es.uniovi.apuntesuniovi.mocks.entities.MockCourseCreator
import es.uniovi.apuntesuniovi.mocks.entities.MockSemesterCreator
import es.uniovi.apuntesuniovi.repositories.CourseRepository
import es.uniovi.apuntesuniovi.services.dtos.assemblers.SemesterAssembler
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
 * Check class SemesterAssembler
 */
@ExtendWith(MockitoExtension::class)
class SemesterAssemblerTest {
  @Mock
  private lateinit var courseRepository: CourseRepository

  private lateinit var semesterAssembler: SemesterAssembler

  /**
   * Create init data for the test
   */
  @BeforeEach
  fun initTest() {
    semesterAssembler = SemesterAssembler(courseRepository)
  }

  /**
   * Checks the conversion with valid Semester
   */
  @Test
  fun validSemester() {
    val semester = MockSemesterCreator().create()
    val dto = semesterAssembler.entityToDto(semester)
    assertEquals(semester.id, dto.id)
    assertEquals(semester.position, dto.position)
    assertEquals(semester.course?.id, dto.courseId)
  }

  /**
   * Checks the conversion with valid Semester without Course
   */
  @Test
  fun validSemesterWithOutCourse() {
    val semester = MockSemesterCreator().create()
    semester.course = null
    val dto = semesterAssembler.entityToDto(semester)
    assertEquals(semester.id, dto.id)
    assertEquals(semester.position, dto.position)
    assertNull(semester.course)
    assertNull(dto.courseId)
  }

  /**
   * Checks the conversion with null Semester
   */
  @Test
  fun nullSemester() {
    try {
      semesterAssembler.entityToDto(null)
      fail("Semester can´t be null")
    } catch (e: IllegalArgumentException) {
      assertEquals(e.message, SemesterMessages.NULL)
    }
  }

  /**
   * Checks the conversion with valid SemesterDto
   */
  @Test
  fun validSemesterDto() {
    val dto = MockSemesterDtoCreator().create()
    Mockito.`when`(courseRepository.findById(dto.courseId!!)).thenReturn(
      Optional.of(MockCourseCreator().create())
    )
    val semester = semesterAssembler.dtoToEntity(dto)
    assertEquals(semester.id, dto.id)
    assertEquals(semester.position, dto.position)
    assertEquals(semester.course?.id, dto.courseId)
  }

  /**
   * Checks the conversion with valid SemesterDto without Course
   */
  @Test
  fun validSemesterDtoCenterNull() {
    val dto = MockSemesterDtoCreator().create()
    dto.courseId = null
    val semester = semesterAssembler.dtoToEntity(dto)
    assertEquals(semester.id, dto.id)
    assertEquals(semester.position, dto.position)
    assertEquals(semester.course?.id, dto.courseId)
    assertNull(semester.course)
    assertNull(dto.courseId)
  }

  /**
   * Checks the conversion with null SemesterDto
   */
  @Test
  fun nullSemesterDto() {
    try {
      semesterAssembler.dtoToEntity(null)
      fail("SemesterDto can´t be null")
    } catch (e: IllegalArgumentException) {
      assertEquals(e.message, SemesterMessages.NULL)
    }
  }
}