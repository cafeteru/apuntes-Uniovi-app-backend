package es.uniovi.apuntesuniovi.services.subjects

import es.uniovi.apuntesuniovi.dtos.Converter
import es.uniovi.apuntesuniovi.dtos.entities.SubjectDto
import es.uniovi.apuntesuniovi.infrastructure.messages.SubjectMessages
import es.uniovi.apuntesuniovi.mocks.entities.MockSubject
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
 * Check class DisableSubject
 */
@ExtendWith(MockitoExtension::class)
class DisableSubjectTest {
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
     * Checks with valid id and existing user
     */
    @Test
    fun validIdAndExistUser() {
        val subject = MockSubject().create()
        val subjectDto = Converter.convert(subject, SubjectDto::class.java)
        Mockito.`when`(subjectRepository.findById(subject.id!!)).thenReturn(Optional.of(subject))
        Mockito.`when`(subjectRepository.save(subject)).thenReturn(subject)
        val result = subjectService.disable(subjectDto.id!!, !subjectDto.active!!)
        assertNotEquals(subjectDto, result)
        assertEquals(subjectDto.id, result.id)
    }

    /**
     * Checks with valid id and not existing user
     */
    @Test
    fun validIdAndNotExistUser() {
        try {
            subjectService.disable(1L, true)
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
            subjectService.disable(-1L, true)
            fail(SubjectMessages.INVALID_ID)
        } catch (e: IllegalArgumentException) {
            assertEquals(e.message, SubjectMessages.INVALID_ID)
        }
    }
}