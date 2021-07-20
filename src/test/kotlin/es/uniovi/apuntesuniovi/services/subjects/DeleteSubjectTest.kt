package es.uniovi.apuntesuniovi.services.subjects

import es.uniovi.apuntesuniovi.infrastructure.messages.SubjectMessages
import es.uniovi.apuntesuniovi.mocks.entities.MockSubject
import es.uniovi.apuntesuniovi.repositories.SubjectRepository
import es.uniovi.apuntesuniovi.services.SubjectService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*
import kotlin.test.fail

/**
 * Check delete method of the SubjectService class
 */
@ExtendWith(MockitoExtension::class)
class DeleteSubjectTest {
    private lateinit var subjectService: SubjectService

    @Mock
    private lateinit var subjectRepository: SubjectRepository

    /**
     * Create init data for the test
     */
    @BeforeEach
    fun initTest() {
        subjectService = SubjectService(subjectRepository)
    }

    /**
     * Checks with valid id and existing Subject
     */
    @Test
    fun validIdAndExistSubject() {
        val subject = MockSubject().create()
        val id = subject.id!!
        Mockito.`when`(subjectRepository.findById(id)).thenReturn(Optional.of(subject))
        val result = subjectService.delete(id)
        assertTrue(result)
        Mockito.verify(subjectRepository, Mockito.times(1)).deleteById(id)
    }

    /**
     * Checks with valid id and not existing Subject
     */
    @Test
    fun validIdAndNotExistSubject() {
        try {
            subjectService.delete(1L)
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
        try {
            subjectService.delete(-1L)
            fail(SubjectMessages.INVALID_ID)
        } catch (e: IllegalArgumentException) {
            assertEquals(e.message, SubjectMessages.INVALID_ID)
        }
    }
}