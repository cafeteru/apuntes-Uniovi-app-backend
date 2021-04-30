package es.uniovi.apuntesuniovi.services.teachSubject

import es.uniovi.apuntesuniovi.dtos.Converter
import es.uniovi.apuntesuniovi.dtos.entities.TeachSubjectDto
import es.uniovi.apuntesuniovi.infrastructure.messages.TeachSubjectMessages
import es.uniovi.apuntesuniovi.mocks.entities.MockTeachSubjectCreator
import es.uniovi.apuntesuniovi.models.TeachSubject
import es.uniovi.apuntesuniovi.repositories.SubjectRepository
import es.uniovi.apuntesuniovi.repositories.TeachSubjectRepository
import es.uniovi.apuntesuniovi.repositories.UserRepository
import es.uniovi.apuntesuniovi.services.TeachSubjectService
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension

/**
 * Check find by id method of the UserService class
 */
@ExtendWith(MockitoExtension::class)
class FindStudentsBySubjectIdTest {
    private lateinit var teachSubject: TeachSubject
    private lateinit var teachSubjectService: TeachSubjectService

    @Mock
    private lateinit var userRepository: UserRepository

    @Mock
    private lateinit var subjectRepository: SubjectRepository

    @Mock
    private lateinit var teachSubjectRepository: TeachSubjectRepository

    /**
     * Create init data for the test
     */
    @BeforeEach
    fun initTest() {
        teachSubject = MockTeachSubjectCreator().create()
        teachSubjectService = TeachSubjectService(teachSubjectRepository, userRepository, subjectRepository)
    }

    /**
     * Checks with valid id and existing user
     */
    @Test
    fun validIdAndExistUser() {
        val id = 1L
        Mockito.`when`(teachSubjectRepository.findBySubjectId(id)).thenReturn(
            listOf(teachSubject)
        )
        val result = teachSubjectService.findTeachersBySubjectId(id)
        assertFalse(result.isEmpty())
        assertEquals(Converter.convert(teachSubject, TeachSubjectDto::class.java).teacherId, result[0].id)
    }

    /**
     * Checks with valid id and not existing user
     */
    @Test
    fun validIdAndNotExistUser() {
        val id = 1L
        val result = teachSubjectService.findTeachersBySubjectId(id)
        assertTrue(result.isEmpty())
    }

    /**
     * Checks with invalid id
     */
    @Test
    fun invalidId() {
        val id = -1L
        try {
            teachSubjectService.findTeachersBySubjectId(id)
            fail(TeachSubjectMessages.INVALID_SUBJECT_ID)
        } catch (e: IllegalArgumentException) {
            assertEquals(e.message, TeachSubjectMessages.INVALID_SUBJECT_ID)
        }
    }
}