package es.uniovi.apuntesuniovi.services.learnSubject

import es.uniovi.apuntesuniovi.dtos.assemblers.LearnSubjectAssembler
import es.uniovi.apuntesuniovi.infrastructure.messages.LearnSubjectMessages
import es.uniovi.apuntesuniovi.mocks.entities.MockLearnSubjectCreator
import es.uniovi.apuntesuniovi.models.LearnSubject
import es.uniovi.apuntesuniovi.repositories.LearnSubjectRepository
import es.uniovi.apuntesuniovi.repositories.SubjectRepository
import es.uniovi.apuntesuniovi.repositories.UserRepository
import es.uniovi.apuntesuniovi.services.LearnSubjectService
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest

/**
 * Check find by id method of the UserService class
 */
@ExtendWith(MockitoExtension::class)
class FindStudentsBySubjectIdTest {
    private lateinit var learnSubject: LearnSubject
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
        learnSubject = MockLearnSubjectCreator().create()
        learnSubjectAssembler = LearnSubjectAssembler(subjectRepository, userRepository)
        learnSubjectService = LearnSubjectService(
            userRepository, subjectRepository, learnSubjectRepository
        )
    }

    /**
     * Checks with valid id and existing user
     */
    @Test
    fun validIdAndExistUser() {
        val id = 1L
        val list = listOf(learnSubject)
        val pageable = PageRequest.of(0, 10)
        val page = PageImpl(list, pageable, list.size.toLong())
        Mockito.`when`(learnSubjectRepository.findBySubjectId(id, pageable)).thenReturn(page)
        val result = learnSubjectService.findStudentsBySubjectId(id, pageable)
        assertFalse(result.isEmpty)
        assertEquals(learnSubjectAssembler.entityToDto(learnSubject).studentId, result.content[0].id)
    }

    /**
     * Checks with valid id and not existing user
     */
    @Test
    fun validIdAndNotExistUser() {
        val id = 1L
        val pageable = PageRequest.of(0, 10)
        Mockito.`when`(learnSubjectRepository.findBySubjectId(id, pageable)).thenReturn(Page.empty())
        val result = learnSubjectService.findStudentsBySubjectId(id, pageable)
        assertTrue(result.isEmpty)
    }

    /**
     * Checks with invalid id
     */
    @Test
    fun invalidId() {
        val id = -1L
        try {
            learnSubjectService.findStudentsBySubjectId(id, PageRequest.of(0, 10))
            fail(LearnSubjectMessages.INVALID_SUBJECT_ID)
        } catch (e: IllegalArgumentException) {
            assertEquals(e.message, LearnSubjectMessages.INVALID_SUBJECT_ID)
        }
    }
}