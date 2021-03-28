package es.uniovi.apuntesuniovi.controllers.subjects

import es.uniovi.apuntesuniovi.controllers.SubjectController
import es.uniovi.apuntesuniovi.mocks.dtos.MockSubjectDtoCreator
import es.uniovi.apuntesuniovi.services.SubjectService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.http.HttpStatus

/**
 * Check the creation method of the SubjectController class
 */
class CreateSubjectTest {
  private lateinit var subjectController: SubjectController
  private lateinit var subjectService: SubjectService

  /**
   * Create init data for the test
   */
  @BeforeEach
  fun initTest() {
    subjectService = Mockito.mock(SubjectService::class.java)
    subjectController = SubjectController(subjectService)
  }

  /**
   * Checks the functionality with valid data
   */
  @Test
  fun validData() {
    val subjectDto = MockSubjectDtoCreator().create()
    Mockito.`when`(subjectService.create(subjectDto)).thenReturn(subjectDto)
    val httpResponse = subjectController.create(subjectDto)
    Assertions.assertEquals(httpResponse.statusCode, HttpStatus.OK)
    Assertions.assertEquals(httpResponse.body, subjectDto)
  }
}