package es.uniovi.apuntesuniovi.dtos

import es.uniovi.apuntesuniovi.dtos.assemblers.TeachSubjectAssembler
import es.uniovi.apuntesuniovi.infrastructure.messages.TeachSubjectMessages
import es.uniovi.apuntesuniovi.mocks.dtos.MockTeachSubjectDtoCreator
import es.uniovi.apuntesuniovi.mocks.entities.MockSubjectCreator
import es.uniovi.apuntesuniovi.mocks.entities.MockTeachSubjectCreator
import es.uniovi.apuntesuniovi.mocks.entities.MockUserCreator
import es.uniovi.apuntesuniovi.repositories.SubjectRepository
import es.uniovi.apuntesuniovi.repositories.UserRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.fail
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*

/**
 * Check class TeachSubjectDtoAssembler
 */
@ExtendWith(MockitoExtension::class)
class TeachSubjectAssemblerTest {
    @Mock
    private lateinit var userRepository: UserRepository

    @Mock
    private lateinit var subjectRepository: SubjectRepository

    private lateinit var assembler: TeachSubjectAssembler

    /**
     * Create init data for the test
     */
    @BeforeEach
    fun initTest() {
        assembler = TeachSubjectAssembler(subjectRepository, userRepository)
    }

    /**
     * Checks the conversion with valid TeachSubject
     */
    @Test
    fun validTeachSubject() {
        val teachSubject = MockTeachSubjectCreator().create()
        val dto = assembler.entityToDto(teachSubject)
        assertEquals(teachSubject.id, dto.id)
        assertEquals(teachSubject.subject.id, dto.subjectId)
        assertEquals(teachSubject.teacher.id, dto.teacherId)
    }

    /**
     * Checks the conversion with valid TeachSubject
     */
    @Test
    fun validTeachNullSubject() {
        val teachSubject = MockTeachSubjectCreator().create()
        val dto = assembler.entityToDto(teachSubject)
        assertEquals(teachSubject.id, dto.id)
        assertEquals(teachSubject.subject.id, dto.subjectId)
        assertEquals(teachSubject.teacher.id, dto.teacherId)
    }

    /**
     * Checks the conversion with null TeachSubject
     */
    @Test
    fun nullTeachSubject() {
        try {
            assembler.entityToDto(null)
            fail(TeachSubjectMessages.NULL)
        } catch (e: IllegalArgumentException) {
            assertEquals(e.message, TeachSubjectMessages.NULL)
        }
    }

    /**
     * Checks the conversion with valid TeachSubjectDto
     */
    @Test
    fun validTeachSubjectDto() {
        val dto = MockTeachSubjectDtoCreator().create()
        Mockito.`when`(subjectRepository.findById(dto.subjectId)).thenReturn(
            Optional.of(MockSubjectCreator().create())
        )
        Mockito.`when`(userRepository.findById(dto.teacherId)).thenReturn(
            Optional.of(MockUserCreator().createTeacher())
        )
        val teachSubject = assembler.dtoToEntity(dto)
        assertEquals(teachSubject.id, dto.id)
        assertEquals(teachSubject.subject.id, dto.subjectId)
        assertEquals(teachSubject.teacher.id, dto.teacherId)
    }

    /**
     * Checks the conversion with null TeachSubjectDto
     */
    @Test
    fun nullTeachSubjectDto() {
        try {
            assembler.dtoToEntity(null)
            fail(TeachSubjectMessages.NULL)
        } catch (e: IllegalArgumentException) {
            assertEquals(e.message, TeachSubjectMessages.NULL)
        }
    }
}