package es.uniovi.apuntesuniovi.services.learnSubject

import es.uniovi.apuntesuniovi.dtos.Converter
import es.uniovi.apuntesuniovi.dtos.entities.LearnSubjectDto
import es.uniovi.apuntesuniovi.infrastructure.messages.LearnSubjectMessages
import es.uniovi.apuntesuniovi.infrastructure.messages.SubjectMessages
import es.uniovi.apuntesuniovi.infrastructure.messages.UserMessages
import es.uniovi.apuntesuniovi.mocks.entities.MockLearnSubject
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
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.fail

/**
 * Check the creation method of the LearnSubjectService class
 */
@ExtendWith(MockitoExtension::class)
class CreateLearnSubjectTest {
    private lateinit var learnSubjectService: LearnSubjectService

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
        learnSubjectService = LearnSubjectService(userRepository, subjectRepository, learnSubjectRepository)
    }

    @Test
    fun validData() {
        val learnSubject = MockLearnSubject().create()
        val dto = Converter.convert(learnSubject, LearnSubjectDto::class.java)
        Mockito.`when`(
            subjectRepository.findById(learnSubject.subject.id!!)
        ).thenReturn(Optional.of(learnSubject.subject))
        Mockito.`when`(
            userRepository.findById(learnSubject.student.id!!)
        ).thenReturn(Optional.of(learnSubject.student))
        Mockito.`when`(
            learnSubjectRepository.saveAll(Mockito.anyList())
        ).thenReturn(listOf(learnSubject))
        val list = listOf(learnSubject)
        val page = PageImpl(list, Pageable.unpaged(), list.size.toLong())
        Mockito.`when`(learnSubjectRepository.findBySubjectId(dto.subjectId!!, Pageable.unpaged())).thenReturn(page)
        val result = learnSubjectService.create(dto.subjectId!!, listOf(dto))
        assertEquals(dto, result[0])
        assertEquals(learnSubject.id, result[0].id)
    }

    @Test
    fun invalidData() {
        val learnSubject = MockLearnSubject().create()
        learnSubject.student.role = RoleType.ROLE_ADMIN
        val dto = Converter.convert(learnSubject, LearnSubjectDto::class.java)
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
            val learnSubject = MockLearnSubject().create()
            val dto = Converter.convert(learnSubject, LearnSubjectDto::class.java)
            learnSubjectService.create(learnSubject.subject.id!!, listOf(dto))
            fail(SubjectMessages.NOT_FOUND)
        } catch (e: IllegalArgumentException) {
            assertEquals(e.message, SubjectMessages.NOT_FOUND)
        }
    }

    @Test
    fun noExistStudent() {
        try {
            val learnSubject = MockLearnSubject().create()
            val dto = Converter.convert(learnSubject, LearnSubjectDto::class.java)
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