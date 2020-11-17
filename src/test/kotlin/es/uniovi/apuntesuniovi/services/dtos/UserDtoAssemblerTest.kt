package es.uniovi.apuntesuniovi.services.dtos

import es.uniovi.apuntesuniovi.infrastructure.constants.ExceptionMessages
import es.uniovi.apuntesuniovi.mocks.MockFactory
import es.uniovi.apuntesuniovi.servicies.dtos.impl.UserDtoAssembler
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.fail

class UserDtoAssemblerTest {
    private val userDtoAssembler = UserDtoAssembler()
    private val mockFactory = MockFactory()

    @Test
    fun validUser() {
        val user = mockFactory.getEntities().createUser()
        val userDto = userDtoAssembler.entityToDto(user);
        assertEquals(user.id, userDto.id)
        assertEquals(user.name, userDto.name)
        assertEquals(user.surname, userDto.surname)
        assertEquals(user.email, userDto.email)
        assertEquals(user.phone, userDto.phone)
        assertEquals(user.active, userDto.active)
        assertEquals(user.img, userDto.img)
        assertEquals(user.birthDate, userDto.birthDate)
        assertEquals(user.username, userDto.username)
        assertEquals(user.password, userDto.password)
        assertEquals(user.role.toString(), userDto.role)
        assertEquals(user.identificationType.toString(), userDto.identificationType)
        assertEquals(user.numberIdentification, userDto.numberIdentification)
    }

    @Test
    fun nullNameUser() {
        val user = mockFactory.getEntities().createUser()
        user.name = null
        val userDto = userDtoAssembler.entityToDto(user);
        assertEquals(user.name, userDto.name)
        assertNull(userDto.name)
    }

    @Test
    fun emptyNameUser() {
        val user = mockFactory.getEntities().createUser()
        user.name = ""
        val userDto = userDtoAssembler.entityToDto(user);
        assertEquals(user.name, userDto.name)
        assertEquals(userDto.name, "")
    }

    @Test
    fun nullUser() {
        try {
            userDtoAssembler.entityToDto(null)
            fail("User can´t be null")
        } catch (e: IllegalArgumentException) {
            assertEquals(e.message, ExceptionMessages.NULL_USER)
        }
    }
}