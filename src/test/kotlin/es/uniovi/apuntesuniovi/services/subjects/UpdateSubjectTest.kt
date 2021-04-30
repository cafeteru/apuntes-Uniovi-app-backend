package es.uniovi.apuntesuniovi.services.subjects

import es.uniovi.apuntesuniovi.dtos.Converter
import es.uniovi.apuntesuniovi.dtos.entities.SubjectDto
import es.uniovi.apuntesuniovi.infrastructure.messages.SubjectMessages
import es.uniovi.apuntesuniovi.mocks.dtos.MockSubjectDtoCreator
import es.uniovi.apuntesuniovi.models.Subject
import es.uniovi.apuntesuniovi.repositories.SubjectRepository
import es.uniovi.apuntesuniovi.services.SubjectService
import org.assertj.core.api.Assertions.fail
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*

/**
 * Check class UpdateSubject
 */
@ExtendWith(MockitoExtension::class)
class UpdateSubjectTest {
    private lateinit var subjectDto: SubjectDto
    private lateinit var subjectService: SubjectService

    @Mock
    private lateinit var subjectRepository: SubjectRepository

    /**
     * Create init data for the test
     */
    @BeforeEach
    fun initTest() {
        subjectDto = MockSubjectDtoCreator().create()
        subjectService = SubjectService(subjectRepository)
    }

    /**
     * Check with valid user and a invalid id
     */
    @Test
    fun invalidId() {
        try {
            val id = 2L
            subjectService.update(id, subjectDto)
            fail(SubjectMessages.NOT_FOUND)
        } catch (e: IllegalArgumentException) {
            assertEquals(e.message, SubjectMessages.NOT_FOUND)
        }
    }

    /**
     * Checks the functionality with valid user
     */
    @Test
    fun validUser() {
        val id = subjectDto.id!!
        val subject = Converter.convert(subjectDto, Subject::class.java)
        Mockito.`when`(subjectRepository.findById(id)).thenReturn(Optional.of(subject))
        Mockito.`when`(subjectRepository.save(Mockito.any(Subject::class.java))).thenReturn(subject)
        val result = subjectService.update(id, subjectDto)
        Assertions.assertNotNull(result)
        assertEquals(result, subjectDto)
    }
}