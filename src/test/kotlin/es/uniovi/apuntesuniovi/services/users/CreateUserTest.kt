package es.uniovi.apuntesuniovi.services.users

import es.uniovi.apuntesuniovi.dtos.Converter
import es.uniovi.apuntesuniovi.dtos.entities.UserDto
import es.uniovi.apuntesuniovi.infrastructure.messages.UserMessages
import es.uniovi.apuntesuniovi.mocks.entities.MockUser
import es.uniovi.apuntesuniovi.models.User
import es.uniovi.apuntesuniovi.repositories.AddressRepository
import es.uniovi.apuntesuniovi.repositories.UserRepository
import es.uniovi.apuntesuniovi.services.UserService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.fail
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

/**
 * Check the creation method of the UserService class
 */
@ExtendWith(MockitoExtension::class)
class CreateUserTest {
    private lateinit var userDto: UserDto
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
        userDto = Converter.convert(
            MockUser().create(),
            UserDto::class.java
        )
    }

    /**
     * Checks the functionality with valid data
     */
    @Test
    fun validData() {
        val user = MockUser().create()
        val userDto = Converter.convert(user, UserDto::class.java)
        Mockito.`when`(userRepository.save(Mockito.any(User::class.java))).thenReturn(user)
        val result = userService.create(userDto)
        assertNotEquals(userDto, result)
        assertEquals(user.id, result.id)
        assertNull(result.img)
        assertNull(result.password)
    }

    /**
     * Checks the functionality with valid user but the user already register
     */
    @Test
    fun existedUser() {
        try {
            val user = MockUser().create()
            val userDto = Converter.convert(user, UserDto::class.java)
            Mockito.`when`(userRepository.findByUsername(user.username!!)).thenReturn(Optional.of(user))
            userService.create(userDto)
            fail(UserMessages.ALREADY_REGISTERED_USERNAME)
        } catch (e: IllegalArgumentException) {
            assertEquals(e.message, UserMessages.ALREADY_REGISTERED_USERNAME)
        }
    }

    /**
     * Checks the functionality with valid user except username is null
     */
    @Test
    fun nullUsername() {
        try {
            userDto.username = null
            userService.create(userDto)
            fail(UserMessages.INVALID_DATA_USER)
        } catch (e: IllegalArgumentException) {
            assertEquals(e.message, UserMessages.INVALID_DATA_USER)
        }
    }

    /**
     * Checks the functionality with valid user except username is empty
     */
    @Test
    fun emptyUsername() {
        try {
            userDto.username = ""
            userService.create(userDto)
            fail(UserMessages.LIMIT_USERNAME)
        } catch (e: IllegalArgumentException) {
            Assertions.assertEquals(e.message, UserMessages.LIMIT_USERNAME)
        }
    }

    /**
     * Checks the functionality with valid user except password is null
     */
    @Test
    fun nullPassword() {
        try {
            userDto.password = null
            userService.create(userDto)
            fail(UserMessages.INVALID_DATA_USER)
        } catch (e: IllegalArgumentException) {
            Assertions.assertEquals(e.message, UserMessages.INVALID_DATA_USER)
        }
    }

    /**
     * Checks the functionality with valid user except password is empty
     */
    @Test
    fun emptyPassword() {
        try {
            userDto.password = ""
            userService.create(userDto)
            fail(UserMessages.LIMIT_PASSWORD)
        } catch (e: IllegalArgumentException) {
            Assertions.assertEquals(e.message, UserMessages.LIMIT_PASSWORD)
        }
    }
}