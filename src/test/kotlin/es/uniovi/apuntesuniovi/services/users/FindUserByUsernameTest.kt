package es.uniovi.apuntesuniovi.services.users

import es.uniovi.apuntesuniovi.dtos.Converter
import es.uniovi.apuntesuniovi.dtos.entities.UserDto
import es.uniovi.apuntesuniovi.infrastructure.messages.UserMessages
import es.uniovi.apuntesuniovi.mocks.entities.MockUserCreator
import es.uniovi.apuntesuniovi.repositories.AddressRepository
import es.uniovi.apuntesuniovi.repositories.UserRepository
import es.uniovi.apuntesuniovi.services.UserService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertNull
import kotlin.test.fail

/**
 * Check find by username method of the UserService class
 */
@ExtendWith(MockitoExtension::class)
class FindUserByUsernameTest {
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
     * Checks the functionality with valid data
     */
    @Test
    fun validData() {
        val username = "username"
        val user = MockUserCreator().create()
        user.username = username
        val userDto = Converter.convert(user, UserDto::class.java)
        Mockito.`when`(userRepository.findByUsername(username)).thenReturn(Optional.of(user))
        val result = userService.findByUsername(username)
        assertNotEquals(userDto, result)
        assertEquals(user.id, result.id)
        assertNull(result.img)
        assertNull(result.password)
    }

    /**
     * Checks the functionality with invalid data
     */
    @Test
    fun invalidData() {
        try {
            userService.findByUsername("username")
            fail(UserMessages.NOT_FOUND_USERNAME)
        } catch (e: IllegalArgumentException) {
            Assertions.assertEquals(e.message, UserMessages.NOT_FOUND_USERNAME)
        }
    }

    /**
     * Checks the functionality with empty data
     */
    @Test
    fun emptyData() {
        try {
            userService.findByUsername("")
            fail(UserMessages.INVALID_USERNAME)
        } catch (e: IllegalArgumentException) {
            Assertions.assertEquals(e.message, UserMessages.INVALID_USERNAME)
        }
    }
}