package es.uniovi.apuntesuniovi.services.dtos

import es.uniovi.apuntesuniovi.infrastructure.messages.CenterMessages
import es.uniovi.apuntesuniovi.mocks.dtos.MockCenterDtoCreator
import es.uniovi.apuntesuniovi.mocks.entities.MockCenterCreator
import es.uniovi.apuntesuniovi.services.dtos.assemblers.CenterAssembler
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.fail

/**
 * Check class CenterDtoAssembler
 */
class CenterAssemblerTest {
    private val centerAssembler = CenterAssembler()

    /**
     * Checks the conversion with valid Center
     */
    @Test
    fun validCenter() {
        val center = MockCenterCreator().create()
        val centerDto = centerAssembler.entityToDto(center)
        assertEquals(center.id, centerDto.id)
        assertEquals(center.name, centerDto.name)
        assertEquals(center.address, centerDto.address)
    }

    /**
     * Checks the conversion with null Center
     */
    @Test
    fun nullCenter() {
        try {
            centerAssembler.entityToDto(null)
            fail("Center can´t be null")
        } catch (e: IllegalArgumentException) {
            assertEquals(e.message, CenterMessages.NULL)
        }
    }

    /**
     * Checks the conversion with valid CenterDto
     */
    @Test
    fun validCenterDto() {
        val centerDto = MockCenterDtoCreator().create()
        val center = centerAssembler.dtoToEntity(centerDto)
        assertEquals(center.id, centerDto.id)
        assertEquals(center.name, centerDto.name)
        assertEquals(center.address, centerDto.address)
    }

    /**
     * Checks the conversion with null CenterDto
     */
    @Test
    fun nullCenterDto() {
        try {
            centerAssembler.dtoToEntity(null)
            fail("CenterDto can´t be null")
        } catch (e: IllegalArgumentException) {
            assertEquals(e.message, CenterMessages.NULL)
        }
    }
}