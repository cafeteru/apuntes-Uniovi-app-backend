package es.uniovi.apuntesuniovi.services.commands.subjects

import es.uniovi.apuntesuniovi.mocks.entities.MockSubjectCreator
import es.uniovi.apuntesuniovi.models.Subject
import es.uniovi.apuntesuniovi.repositories.SubjectRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension

/**
 * Check class CreateSubjectService
 */
@ExtendWith(MockitoExtension::class)
class CreateSubjectServiceTest {
    private lateinit var subject: Subject

    @Mock
    private lateinit var subjectRepository: SubjectRepository

    /**
     * Create init data for the test
     */
    @BeforeEach
    fun initTest() {
        subject = MockSubjectCreator().create()
    }

    /**
     * Checks the functionality with valid data
     */
    @Test
    fun validData() {
        subject.id = null
        Mockito.`when`(subjectRepository.save(subject)).thenReturn(MockSubjectCreator().create())
        val result = CreateSubjectService(subjectRepository, subject).execute()
        assertEquals(result.name, subject.name)
        assertEquals(result.subjectType, subject.subjectType)
        assertNotEquals(result.id, subject.id)
    }
}