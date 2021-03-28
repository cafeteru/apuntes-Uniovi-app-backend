package es.uniovi.apuntesuniovi.controllers.subjects

import es.uniovi.apuntesuniovi.controllers.SubjectController
import es.uniovi.apuntesuniovi.dtos.entities.SubjectDto
import es.uniovi.apuntesuniovi.mocks.dtos.MockSubjectDtoCreator
import es.uniovi.apuntesuniovi.services.SubjectService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import java.util.*


/**
 * Check find all method of the UserController class
 */
class FindAllSubjectsTest {
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

  @Test
  fun findAllTest() {
    val subjectDto = MockSubjectDtoCreator().create()
    val list: List<SubjectDto> = listOf(subjectDto)
    val pageable = PageRequest.of(0, 5)
    val page: Page<SubjectDto> = PageImpl(list, pageable, 1)
    Mockito.`when`(subjectService.findAll(pageable, subjectDto)).thenReturn(page)
    val httpResponse: ResponseEntity<Page<SubjectDto>> = subjectController.findAll(pageable, subjectDto)
    assertEquals(httpResponse.statusCode, HttpStatus.OK)
    assertEquals(httpResponse.body, page)
  }

  @Test
  fun findAllEmptyTest() {
    val pageable = PageRequest.of(0, 5)
    val page: Page<SubjectDto> = PageImpl(ArrayList(), pageable, 1)
    val subjectDto = MockSubjectDtoCreator().create()
    Mockito.`when`(subjectService.findAll(pageable, subjectDto)).thenReturn(page)
    val httpResponse: ResponseEntity<Page<SubjectDto>> =
      subjectController.findAll(pageable, subjectDto)
    assertEquals(httpResponse.statusCode, HttpStatus.NO_CONTENT)
  }
}