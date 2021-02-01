package es.uniovi.apuntesuniovi.services

import es.uniovi.apuntesuniovi.mocks.dtos.MockSubjectDtoCreator
import es.uniovi.apuntesuniovi.repositories.SubjectRepository
import es.uniovi.apuntesuniovi.services.dtos.assemblers.SubjectAssembler
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
  private val dto = MockSubjectDtoCreator().create()

  @Mock
  private lateinit var subjectRepository: SubjectRepository
  private val subjectAssembler = SubjectAssembler()

  /**
   * Create init data for the test
   */
  @BeforeEach
  fun initTest() {
    subjectService = SubjectService(subjectRepository, subjectAssembler)
  }

  /**
   * Checks the functionality with valid data
   */
  @Test
  fun createValidData() {
    val subject = subjectAssembler.dtoToEntity(dto)
    Mockito.`when`(subjectRepository.save(subject)).thenReturn(subject)
    val result = subjectService.create(dto)
    assertEquals(result.name, subject.name)
    assertEquals(result.subjectType, subject.subjectType.toString())
    assertEquals(result.id, subject.id)
  }
}