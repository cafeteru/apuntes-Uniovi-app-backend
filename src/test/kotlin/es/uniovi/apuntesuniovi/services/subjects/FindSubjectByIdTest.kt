package es.uniovi.apuntesuniovi.services.subjects

import es.uniovi.apuntesuniovi.dtos.Converter
import es.uniovi.apuntesuniovi.dtos.entities.SubjectDto
import es.uniovi.apuntesuniovi.infrastructure.messages.SubjectMessages
import es.uniovi.apuntesuniovi.mocks.entities.MockSubjectCreator
import es.uniovi.apuntesuniovi.models.Subject
import es.uniovi.apuntesuniovi.repositories.SubjectRepository
import es.uniovi.apuntesuniovi.services.SubjectService
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*

/**
 * Check find by id method of the UserService class
 */
@ExtendWith(MockitoExtension::class)
class FindSubjectByIdTest {
    private lateinit var subject: Subject
    private lateinit var subjectService: SubjectService

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
     * Checks with valid id and existing user
     */
    @Test
    fun validIdAndExistUser() {
        val id = 1L
        Mockito.`when`(subjectRepository.findById(id)).thenReturn(Optional.of(subject))
        val result = subjectService.findById(id)
        assertNotNull(result)
        assertEquals(Converter.convert(subject, SubjectDto::class.java), result)
    }

    /**
     * Checks with valid id and not existing user
     */
    @Test
    fun validIdAndNotExistUser() {
        val id = 1L
        try {
            subjectService.findById(id)
            fail(SubjectMessages.NOT_FOUND)
        } catch (e: IllegalArgumentException) {
            assertEquals(e.message, SubjectMessages.NOT_FOUND)
        }
    }

    /**
     * Checks with invalid id
     */
    @Test
    fun invalidId() {
        val id = -1L
        try {
            subjectService.findById(id)
            fail(SubjectMessages.INVALID_ID)
        } catch (e: IllegalArgumentException) {
            assertEquals(e.message, SubjectMessages.INVALID_ID)
        }
    }
}