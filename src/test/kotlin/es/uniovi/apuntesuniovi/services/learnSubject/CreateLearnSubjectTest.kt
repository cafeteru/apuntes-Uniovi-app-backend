package es.uniovi.apuntesuniovi.services.learnSubject

import es.uniovi.apuntesuniovi.dtos.assemblers.LearnSubjectAssembler
import es.uniovi.apuntesuniovi.infrastructure.messages.LearnSubjectMessages
import es.uniovi.apuntesuniovi.infrastructure.messages.SubjectMessages
import es.uniovi.apuntesuniovi.infrastructure.messages.UserMessages
import es.uniovi.apuntesuniovi.mocks.entities.MockLearnSubjectCreator
import es.uniovi.apuntesuniovi.models.types.RoleType
import es.uniovi.apuntesuniovi.repositories.LearnSubjectRepository
import es.uniovi.apuntesuniovi.repositories.SubjectRepository
import es.uniovi.apuntesuniovi.repositories.UserRepository
import es.uniovi.apuntesuniovi.services.LearnSubjectService
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
 * Check the creation method of the LearnSubjectService class
 */
@ExtendWith(MockitoExtension::class)
class CreateLearnSubjectTest {
    private lateinit var learnSubjectService: LearnSubjectService
    private lateinit var learnSubjectAssembler: LearnSubjectAssembler

    @Mock
    private lateinit var userRepository: UserRepository

    @Mock
    private lateinit var subjectRepository: SubjectRepository

    @Mock
    private lateinit var learnSubjectRepository: LearnSubjectRepository


    /**
     * Create init data for the test
     */
    @BeforeEach
    fun initTest() {
        learnSubjectAssembler = LearnSubjectAssembler(subjectRepository, userRepository)
        learnSubjectService = LearnSubjectService(
            userRepository, subjectRepository, learnSubjectRepository
        )
    }

    /**
     * Checks the functionality with valid data
     */
    @Test
    fun validData() {
        val learnSubject = MockLearnSubjectCreator().create()
        val dto = learnSubjectAssembler.entityToDto(learnSubject)
        Mockito.`when`(
            subjectRepository.findById(learnSubject.subject.id!!)
        ).thenReturn(Optional.of(learnSubject.subject))
        Mockito.`when`(
            userRepository.findById(learnSubject.student.id!!)
        ).thenReturn(Optional.of(learnSubject.student))
        Mockito.`when`(
            learnSubjectRepository.saveAll(Mockito.anyList())
        ).thenReturn(listOf(learnSubject))
        val result = learnSubjectService.create(learnSubject.subject.id!!, listOf(dto))
        assertEquals(dto, result[0])
        assertEquals(learnSubject.id, result[0].id)
    }

    /**
     * Checks the functionality with valid data
     */
    @Test
    fun invalidData() {
        val learnSubject = MockLearnSubjectCreator().create()
        learnSubject.student.role = RoleType.STUDENT
        val dto = learnSubjectAssembler.entityToDto(learnSubject)
        Mockito.`when`(
            subjectRepository.findById(learnSubject.subject.id!!)
        ).thenReturn(Optional.of(learnSubject.subject))
        Mockito.`when`(
            userRepository.findById(learnSubject.student.id!!)
        ).thenReturn(Optional.of(learnSubject.student))
        try {
            learnSubjectService.create(learnSubject.subject.id!!, listOf(dto))
        } catch (e: IllegalArgumentException) {
            assertEquals(e.message, LearnSubjectMessages.INVALID_USER_ROLE)
        }
    }

    @Test
    fun noExistSubject() {
        try {
            val learnSubject = MockLearnSubjectCreator().create()
            val dto = learnSubjectAssembler.entityToDto(learnSubject)
            learnSubjectService.create(learnSubject.subject.id!!, listOf(dto))
            fail(SubjectMessages.NOT_FOUND)
        } catch (e: IllegalArgumentException) {
            assertEquals(e.message, SubjectMessages.NOT_FOUND)
        }
    }

    @Test
    fun noExistStudent() {
        try {
            val learnSubject = MockLearnSubjectCreator().create()
            val dto = learnSubjectAssembler.entityToDto(learnSubject)
            Mockito.`when`(
                subjectRepository.findById(learnSubject.subject.id!!)
            ).thenReturn(Optional.of(learnSubject.subject))
            learnSubjectService.create(learnSubject.subject.id!!, listOf(dto))
            fail(UserMessages.NOT_FOUND)
        } catch (e: IllegalArgumentException) {
            assertEquals(e.message, UserMessages.NOT_FOUND)
        }
    }
}