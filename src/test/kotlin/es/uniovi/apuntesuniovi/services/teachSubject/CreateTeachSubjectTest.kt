package es.uniovi.apuntesuniovi.services.teachSubject

import es.uniovi.apuntesuniovi.dtos.assemblers.TeachSubjectAssembler
import es.uniovi.apuntesuniovi.infrastructure.messages.SubjectMessages
import es.uniovi.apuntesuniovi.infrastructure.messages.TeachSubjectMessages
import es.uniovi.apuntesuniovi.infrastructure.messages.UserMessages
import es.uniovi.apuntesuniovi.mocks.entities.MockTeachSubjectCreator
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
    private lateinit var teachSubjectService: TeachSubjectService
    private lateinit var teachSubjectAssembler: TeachSubjectAssembler

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
        teachSubjectAssembler = TeachSubjectAssembler(subjectRepository, userRepository)
        teachSubjectService = TeachSubjectService(
            userRepository, subjectRepository, teachSubjectRepository
        )
    }

    /**
     * Checks the functionality with valid data
     */
    @Test
    fun validData() {
        val teachSubject = MockTeachSubjectCreator().create()
        val dto = teachSubjectAssembler.entityToDto(teachSubject)
        Mockito.`when`(
            subjectRepository.findById(teachSubject.subject.id!!)
        ).thenReturn(Optional.of(teachSubject.subject))
        Mockito.`when`(
            userRepository.findById(teachSubject.teacher.id!!)
        ).thenReturn(Optional.of(teachSubject.teacher))
        Mockito.`when`(
            teachSubjectRepository.saveAll(Mockito.anyList())
        ).thenReturn(listOf(teachSubject))
        val result = teachSubjectService.create(teachSubject.subject.id!!, listOf(dto))
        assertEquals(dto, result[0])
        assertEquals(teachSubject.id, result[0].id)
    }

    /**
     * Checks the functionality with valid data
     */
    @Test
    fun invalidData() {
        val teachSubject = MockTeachSubjectCreator().create()
        teachSubject.teacher.role = RoleType.ROLE_STUDENT
        val dto = teachSubjectAssembler.entityToDto(teachSubject)
        Mockito.`when`(
            subjectRepository.findById(teachSubject.subject.id!!)
        ).thenReturn(Optional.of(teachSubject.subject))
        Mockito.`when`(
            userRepository.findById(teachSubject.teacher.id!!)
        ).thenReturn(Optional.of(teachSubject.teacher))
        try {
            teachSubjectService.create(teachSubject.subject.id!!, listOf(dto))
        } catch (e: IllegalArgumentException) {
            assertEquals(e.message, TeachSubjectMessages.INVALID_USER_ROLE)
        }
    }

    @Test
    fun noExistSubject() {
        try {
            val teachSubject = MockTeachSubjectCreator().create()
            val dto = teachSubjectAssembler.entityToDto(teachSubject)
            teachSubjectService.create(teachSubject.subject.id!!, listOf(dto))
            fail(SubjectMessages.NOT_FOUND)
        } catch (e: IllegalArgumentException) {
            assertEquals(e.message, SubjectMessages.NOT_FOUND)
        }
    }

    @Test
    fun noExistTeacher() {
        try {
            val teachSubject = MockTeachSubjectCreator().create()
            val dto = teachSubjectAssembler.entityToDto(teachSubject)
            Mockito.`when`(
                subjectRepository.findById(teachSubject.subject.id!!)
            ).thenReturn(Optional.of(teachSubject.subject))
            teachSubjectService.create(teachSubject.subject.id!!, listOf(dto))
            fail(UserMessages.NOT_FOUND)
        } catch (e: IllegalArgumentException) {
            assertEquals(e.message, UserMessages.NOT_FOUND)
        }
    }
}