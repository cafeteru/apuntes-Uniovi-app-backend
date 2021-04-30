package es.uniovi.apuntesuniovi.services.users

import es.uniovi.apuntesuniovi.dtos.Converter
import es.uniovi.apuntesuniovi.dtos.entities.UserDto
import es.uniovi.apuntesuniovi.infrastructure.messages.UserMessages
import es.uniovi.apuntesuniovi.mocks.entities.MockUserCreator
import es.uniovi.apuntesuniovi.repositories.AddressRepository
import es.uniovi.apuntesuniovi.repositories.UserRepository
import es.uniovi.apuntesuniovi.services.UserService
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*

/**
 * Check find by id method of the UserService class
 */
@ExtendWith(MockitoExtension::class)
class FindUserByIdTest {
    private lateinit var userService: UserService

    @Mock
    private lateinit var userRepository: UserRepository

    @Mock
    private lateinit var addressRepository: AddressRepository

    /**
     * Create init data for the test
     */
    @BeforeEach
    fun initTest() {
        userService = UserService(userRepository, addressRepository)
    }

    /**
     * Checks with valid id and existing user
     */
    @Test
    fun validIdAndExistUser() {
        val id = 1L
        val user = MockUserCreator().create()
        user.id = 1L
        val userDto = Converter.convert(user, UserDto::class.java)
        Mockito.`when`(userRepository.findById(id)).thenReturn(Optional.of(user))
        val result = userService.findById(id)
        assertNotEquals(userDto, result)
        assertEquals(user.id, result.id)
        assertNotNull(result.img)
        assertNull(result.password)
    }

    /**
     * Checks with valid id and not existing user
     */
    @Test
    fun validIdAndNotExistUser() {
        try {
            userService.findById(1L)
            fail(UserMessages.NOT_FOUND)
        } catch (e: IllegalArgumentException) {
            assertEquals(e.message, UserMessages.NOT_FOUND)
        }
    }

    /**
     * Checks with invalid id
     */
    @Test
    fun invalidId() {
        try {
            userService.findById(-1L)
            fail(UserMessages.INVALID_ID)
        } catch (e: IllegalArgumentException) {
            assertEquals(e.message, UserMessages.INVALID_ID)
        }
    }
}