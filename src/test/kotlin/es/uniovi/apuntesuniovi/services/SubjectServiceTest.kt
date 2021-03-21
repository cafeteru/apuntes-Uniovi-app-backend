package es.uniovi.apuntesuniovi.services

import es.uniovi.apuntesuniovi.mocks.entities.MockSubjectCreator
import es.uniovi.apuntesuniovi.models.Subject
import es.uniovi.apuntesuniovi.repositories.SubjectRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension

/**
 * Check class SubjectService
 */
@ExtendWith(MockitoExtension::class)
class SubjectServiceTest {
  private lateinit var subjectService: SubjectService
  private lateinit var subject: Subject

  @Mock
  private lateinit var subjectRepository: SubjectRepository

  /**
   * Create init data for the test
   */
  @BeforeEach
  fun initTest() {
    subject = MockSubjectCreator().create()
    subjectService = SubjectService(subjectRepository)
  }

  /**
   * Checks the functionality with valid data
   */
  @Test
  fun createValidData() {
    Mockito.`when`(subjectRepository.save(subject)).thenReturn(subject)
    val result = subjectService.create(this.subject)
    assertEquals(result.name, subject.name)
    assertEquals(result.subjectType, subject.subjectType)
    assertEquals(result.id, subject.id)
  }
}