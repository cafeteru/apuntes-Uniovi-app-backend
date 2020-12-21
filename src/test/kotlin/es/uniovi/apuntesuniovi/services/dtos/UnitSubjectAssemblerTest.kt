package es.uniovi.apuntesuniovi.services.dtos

import es.uniovi.apuntesuniovi.infrastructure.messages.UnitSubjectMessages
import es.uniovi.apuntesuniovi.mocks.dtos.MockUnitSubjectDtoCreator
import es.uniovi.apuntesuniovi.mocks.entities.MockSubjectCreator
import es.uniovi.apuntesuniovi.mocks.entities.MockUnitSubjectCreator
import es.uniovi.apuntesuniovi.repositories.SubjectRepository
import es.uniovi.apuntesuniovi.services.dtos.assemblers.UnitSubjectAssembler
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.fail
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*
import kotlin.test.assertNull

/**
 * Check class UnitSubjectAssembler
 */
@ExtendWith(MockitoExtension::class)
class UnitSubjectAssemblerTest {
    @Mock
    private lateinit var subjectRepository: SubjectRepository

    private lateinit var assembler: UnitSubjectAssembler

    /**
     * Create init data for the test
     */
    @BeforeEach
    fun initTest() {
        assembler = UnitSubjectAssembler(subjectRepository)
    }

    /**
     * Checks the conversion with valid UnitSubject
     */
    @Test
    fun validUnitSubject() {
        val unitSubject = MockUnitSubjectCreator().create()
        val dto = assembler.entityToDto(unitSubject)
        assertEquals(unitSubject.id, dto.id)
        assertEquals(unitSubject.name, dto.name)
        assertEquals(unitSubject.position, dto.position)
        assertEquals(unitSubject.subject?.id, dto.subjectId)
    }

    /**
     * Checks the conversion with valid UnitSubject without subject
     */
    @Test
    fun validUnitSubjectNullsubject() {
        val unitSubject = MockUnitSubjectCreator().create()
        unitSubject.subject = null
        val dto = assembler.entityToDto(unitSubject)
        assertEquals(unitSubject.id, dto.id)
        assertEquals(unitSubject.name, dto.name)
        assertEquals(unitSubject.position, dto.position)
        assertEquals(unitSubject.subject?.id, dto.subjectId)
    }

    /**
     * Checks the conversion with null UnitSubject
     */
    @Test
    fun nullUnitSubject() {
        try {
            assembler.entityToDto(null)
            fail("UnitSubject can´t be null")
        } catch (e: IllegalArgumentException) {
            assertEquals(e.message, UnitSubjectMessages.NULL)
        }
    }

    /**
     * Checks the conversion with valid subjectDto
     */
    @Test
    fun validUnitSubjectDto() {
        val dto = MockUnitSubjectDtoCreator().create()
        Mockito.`when`(subjectRepository.findById(dto.subjectId!!)).thenReturn(
            Optional.of(MockSubjectCreator().create())
        )
        val unitSubject = assembler.dtoToEntity(dto)
        assertEquals(unitSubject.id, dto.id)
        assertEquals(unitSubject.name, dto.name)
        assertEquals(unitSubject.position, dto.position)
        assertEquals(unitSubject.subject?.id, dto.subjectId)
    }

    /**
     * Checks the conversion with valid subjectDto without subjectId
     */
    @Test
    fun validUnitSubjectDtoNullSubjectId() {
        val dto = MockUnitSubjectDtoCreator().create()
        dto.subjectId = null
        val unitSubject = assembler.dtoToEntity(dto)
        assertEquals(unitSubject.id, dto.id)
        assertEquals(unitSubject.position, dto.position)
        assertNull(unitSubject.subject)
        assertNull(dto.subjectId)
    }

    /**
     * Checks the conversion with null subjectDto
     */
    @Test
    fun nullUnitSubjectDto() {
        try {
            assembler.dtoToEntity(null)
            fail("UnitSubjectDto can´t be null")
        } catch (e: IllegalArgumentException) {
            assertEquals(e.message, UnitSubjectMessages.NULL)
        }
    }
}