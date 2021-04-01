package es.uniovi.apuntesuniovi.dtos

import es.uniovi.apuntesuniovi.dtos.assemblers.TeachSubjectRegistryAssembler
import es.uniovi.apuntesuniovi.infrastructure.messages.TeachSubjectRegistryMessages
import es.uniovi.apuntesuniovi.mocks.dtos.MockTeachSubjectRegistryDtoCreator
import es.uniovi.apuntesuniovi.mocks.entities.MockTeachSubjectCreator
import es.uniovi.apuntesuniovi.mocks.entities.MockTeachSubjectRegistryCreator
import es.uniovi.apuntesuniovi.repositories.TeachSubjectRepository
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
 * Check class TeachSubjectRegistryDtoAssembler
 */
@ExtendWith(MockitoExtension::class)
class TeachSubjectRegistryRegistryAssemblerTest {
    @Mock
    private lateinit var teachSubjectRepository: TeachSubjectRepository

    private lateinit var assembler: TeachSubjectRegistryAssembler

    /**
     * Create init data for the test
     */
    @BeforeEach
    fun initTest() {
        assembler = TeachSubjectRegistryAssembler(teachSubjectRepository)
    }

    /**
     * Checks the conversion with valid TeachSubjectRegistry
     */
    @Test
    fun validTeachSubjectRegistry() {
        val teachSubjectRegistry = MockTeachSubjectRegistryCreator().create()
        val dto = assembler.entityToDto(teachSubjectRegistry)
        assertEquals(teachSubjectRegistry.id, dto.id)
        assertEquals(teachSubjectRegistry.teachSubject.id, dto.teachSubjectId)
        assertEquals(teachSubjectRegistry.initDay, dto.initDay)
        assertEquals(teachSubjectRegistry.finishDay, dto.finishDay)
    }

    /**
     * Checks the conversion with valid TeachSubjectRegistry
     */
    @Test
    fun validTeachNullSubject() {
        val teachSubjectRegistry = MockTeachSubjectRegistryCreator().create()
        val dto = assembler.entityToDto(teachSubjectRegistry)
        assertEquals(teachSubjectRegistry.id, dto.id)
        assertEquals(teachSubjectRegistry.teachSubject.id, dto.teachSubjectId)
        assertEquals(teachSubjectRegistry.initDay, dto.initDay)
        assertEquals(teachSubjectRegistry.finishDay, dto.finishDay)
    }

    /**
     * Checks the conversion with null TeachSubjectRegistry
     */
    @Test
    fun nullTeachSubjectRegistry() {
        try {
            assembler.entityToDto(null)
            fail("TeachSubjectRegistry can´t be null")
        } catch (e: IllegalArgumentException) {
            assertEquals(e.message, TeachSubjectRegistryMessages.NULL)
        }
    }

    /**
     * Checks the conversion with valid TeachSubjectRegistryDto
     */
    @Test
    fun validTeachSubjectRegistryDto() {
        val dto = MockTeachSubjectRegistryDtoCreator().create()
        Mockito.`when`(teachSubjectRepository.findById(dto.teachSubjectId!!)).thenReturn(
            Optional.of(MockTeachSubjectCreator().create())
        )
        val teachSubjectRegistry = assembler.dtoToEntity(dto)
        assertEquals(teachSubjectRegistry.id, dto.id)
        assertEquals(teachSubjectRegistry.teachSubject.id, dto.teachSubjectId)
        assertEquals(teachSubjectRegistry.initDay, dto.initDay)
        assertEquals(teachSubjectRegistry.finishDay, dto.finishDay)
    }

    /**
     * Checks the conversion with null TeachSubjectRegistryDto
     */
    @Test
    fun nullTeachSubjectRegistryDto() {
        try {
            assembler.dtoToEntity(null)
            fail("TeachSubjectRegistryDto can´t be null")
        } catch (e: IllegalArgumentException) {
            assertEquals(e.message, TeachSubjectRegistryMessages.NULL)
        }
    }
}