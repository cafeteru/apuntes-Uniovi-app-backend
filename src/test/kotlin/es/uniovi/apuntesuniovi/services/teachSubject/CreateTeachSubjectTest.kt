package es.uniovi.apuntesuniovi.services.teachSubject

import es.uniovi.apuntesuniovi.dtos.Converter
import es.uniovi.apuntesuniovi.dtos.entities.TeachSubjectDto
import es.uniovi.apuntesuniovi.infrastructure.messages.SubjectMessages
import es.uniovi.apuntesuniovi.infrastructure.messages.TeachSubjectMessages
import es.uniovi.apuntesuniovi.infrastructure.messages.UserMessages
import es.uniovi.apuntesuniovi.mocks.entities.MockTeachSubject
import es.uniovi.apuntesuniovi.models.TeachSubject
import es.uniovi.apuntesuniovi.models.types.RoleType
import es.uniovi.apuntesuniovi.repositories.SubjectRepository
import es.uniovi.apuntesuniovi.repositories.TeachSubjectRepository
import es.uniovi.apuntesuniovi.repositories.UserRepository
import es.uniovi.apuntesuniovi.services.TeachSubjectService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.fail

/**
 * Check the creation method of the TeachSubjectService class
 */
@ExtendWith(MockitoExtension::class)
class CreateTeachSubjectTest {
    private lateinit var teachSubject: TeachSubject
    private lateinit var teachSubjectDto: TeachSubjectDto
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
        teachSubjectService = TeachSubjectService(teachSubjectRepository, userRepository, subjectRepository)
        teachSubject = MockTeachSubject().create()
        teachSubjectDto = Converter.convert(teachSubject, TeachSubjectDto::class.java)
    }

    /**
     * Checks the functionality with valid data
     */
    @Test
    fun validData() {
        Mockito.`when`(
            subjectRepository.findById(teachSubject.subject.id!!)
        ).thenReturn(Optional.of(teachSubject.subject))
        Mockito.`when`(
            userRepository.findById(teachSubject.teacher.id!!)
        ).thenReturn(Optional.of(teachSubject.teacher))
        Mockito.`when`(
            teachSubjectRepository.saveAll(Mockito.anyList())
        ).thenReturn(listOf(teachSubject))
        val result = teachSubjectService.create(teachSubject.subject.id!!, listOf(teachSubjectDto))
        assertEquals(teachSubjectDto, result[0])
        assertEquals(teachSubject.id, result[0].id)
    }

    /**
     * Checks the functionality with valid data
     */
    @Test
    fun invalidData() {
        teachSubject.teacher.role = RoleType.ROLE_STUDENT
        teachSubjectDto = Converter.convert(teachSubject, TeachSubjectDto::class.java)
        Mockito.`when`(
            subjectRepository.findById(teachSubject.subject.id!!)
        ).thenReturn(Optional.of(teachSubject.subject))
        Mockito.`when`(
            userRepository.findById(teachSubject.teacher.id!!)
        ).thenReturn(Optional.of(teachSubject.teacher))
        try {
            teachSubjectService.create(teachSubject.subject.id!!, listOf(teachSubjectDto))
        } catch (e: IllegalArgumentException) {
            assertEquals(e.message, TeachSubjectMessages.INVALID_USER_ROLE)
        }
    }

    @Test
    fun noExistSubject() {
        try {
            teachSubjectService.create(teachSubject.subject.id!!, listOf(teachSubjectDto))
            fail(SubjectMessages.NOT_FOUND)
        } catch (e: IllegalArgumentException) {
            assertEquals(e.message, SubjectMessages.NOT_FOUND)
        }
    }

    @Test
    fun noExistTeacher() {
        try {
            Mockito.`when`(
                subjectRepository.findById(teachSubject.subject.id!!)
            ).thenReturn(Optional.of(teachSubject.subject))
            teachSubjectService.create(teachSubject.subject.id!!, listOf(teachSubjectDto))
            fail(UserMessages.NOT_FOUND)
        } catch (e: IllegalArgumentException) {
            assertEquals(e.message, UserMessages.NOT_FOUND)
        }
    }
}