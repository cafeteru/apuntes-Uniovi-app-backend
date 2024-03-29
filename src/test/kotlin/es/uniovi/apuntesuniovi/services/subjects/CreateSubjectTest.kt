package es.uniovi.apuntesuniovi.services.subjects

import es.uniovi.apuntesuniovi.dtos.Converter
import es.uniovi.apuntesuniovi.dtos.entities.SubjectDto
import es.uniovi.apuntesuniovi.mocks.entities.MockSubject
import es.uniovi.apuntesuniovi.models.Subject
import es.uniovi.apuntesuniovi.repositories.SubjectRepository
import es.uniovi.apuntesuniovi.services.SubjectService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import kotlin.test.assertEquals

/**
 * Check the creation method of the SubjectService class
 */
@ExtendWith(MockitoExtension::class)
class CreateSubjectTest {
    @Mock
    private lateinit var subjectRepository: SubjectRepository
    private lateinit var subjectService: SubjectService

    /**
     * Create init data for the test
     */
    @BeforeEach
    fun initTest() {
        subjectService = SubjectService(subjectRepository)
    }

    /**
     * Checks the functionality with valid data
     */
    @Test
    fun validData() {
        val subject = MockSubject().create()
        val subjectDto = Converter.convert(subject, SubjectDto::class.java)
        Mockito.`when`(subjectRepository.save(Mockito.any(Subject::class.java))).thenReturn(subject)
        val result = subjectService.create(subjectDto)
        assertEquals(subjectDto, result)
        assertEquals(subject.id, result.id)
    }
}