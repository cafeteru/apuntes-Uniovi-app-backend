package es.uniovi.apuntesuniovi.services.dtos

import es.uniovi.apuntesuniovi.infrastructure.exceptions.messages.UserMessages
import es.uniovi.apuntesuniovi.mocks.MockFactory
import es.uniovi.apuntesuniovi.servicies.dtos.impl.UserDtoAssembler
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.fail

/**
 * Check class UserDtoAssembler
 */
class UserDtoAssemblerTest {
    private val userDtoAssembler = UserDtoAssembler()
    private val mockFactory = MockFactory()

    /**
     * Checks the conversion with valid user
     */
    @Test
    fun validUser() {
        val user = mockFactory.getEntities().createUser()
        val userDto = userDtoAssembler.entityToDto(user)
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

    /**
     * Checks the conversion with null user
     */
    @Test
    fun nullUser() {
        try {
            userDtoAssembler.entityToDto(null)
            fail("User can´t be null")
        } catch (e: IllegalArgumentException) {
            assertEquals(e.message, UserMessages.NULL_USER)
        }
    }

    /**
     * Checks the conversion with valid userDto
     */
    @Test
    fun validUserDto() {
        val userDto = mockFactory.getDtos().createUserDto()
        val user = userDtoAssembler.dtoToEntity(userDto)
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

    /**
     * Checks the conversion with null userDto
     */
    @Test
    fun nullUserDto() {
        try {
            userDtoAssembler.dtoToEntity(null)
            fail("UserDto can´t be null")
        } catch (e: IllegalArgumentException) {
            assertEquals(e.message, UserMessages.NULL_USER)
        }
    }
}