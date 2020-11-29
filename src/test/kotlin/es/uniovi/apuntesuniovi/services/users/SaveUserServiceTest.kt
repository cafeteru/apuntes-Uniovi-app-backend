package es.uniovi.apuntesuniovi.services.users

import es.uniovi.apuntesuniovi.entities.User
import es.uniovi.apuntesuniovi.infrastructure.exceptions.messages.ExceptionMessagesUser
import es.uniovi.apuntesuniovi.mocks.MockFactory
import es.uniovi.apuntesuniovi.repositories.UserRepository
import es.uniovi.apuntesuniovi.servicies.dtos.entities.UserDto
import es.uniovi.apuntesuniovi.servicies.dtos.impl.UserDtoAssembler
import es.uniovi.apuntesuniovi.servicies.impl.users.SaveUserService
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.util.*

/**
 * Check class SaveUserService
 */
@ExtendWith(MockitoExtension::class)
class SaveUserServiceTest {
    private lateinit var user: User
    private lateinit var userDto: UserDto
    private val encoder = BCryptPasswordEncoder()

    @Mock
    private lateinit var userRepository: UserRepository
    private val userDtoAssembler = UserDtoAssembler()

    /**
     * Create init data for the test
     */
    @BeforeEach
    fun initTest() {
        val mockFactory = MockFactory()
        userDto = mockFactory.getDtos().createUserDto()
        user = userDtoAssembler.dtoToEntity(userDto)
        user.password = encoder.encode(user.password)
    }

    /**
     * Checks the functionality with valid user
     */
    @Test
    fun validUser() {
        Mockito.`when`(userRepository.save(Mockito.any(User::class.java))).thenReturn(user)
        val saveUserService = SaveUserService(userRepository, userDtoAssembler, userDto)
        val result = saveUserService.execute()
        assertNotNull(result)
        assertEquals(result.size, 1)
        assertNotEquals(result[0].password, userDto.password)
    }

    /**
     * Checks the functionality with null user
     */
    @Test
    fun nullUser() {
        try {
            val saveUserService = SaveUserService(userRepository, userDtoAssembler, null)
            saveUserService.execute()
            fail()
        } catch (e: IllegalArgumentException) {
            assertEquals(e.message, ExceptionMessagesUser.INVALID_DATA_USER)
        }
    }

    /**
     * Checks the functionality with valid user but the user already register
     */
    @Test
    fun existedUser() {
        Mockito.`when`(userRepository.findByUsername(userDto.username!!)).thenReturn(Optional.of(user))
        try {
            val saveUserService = SaveUserService(userRepository, userDtoAssembler, userDto)
            saveUserService.execute()
            fail("User already registered")
        } catch (e: IllegalArgumentException) {
            assertEquals(e.message, ExceptionMessagesUser.ALREADY_REGISTERED_USERNAME)
        }
    }

    /**
     * Checks the functionality with valid user except username is null
     */
    @Test
    fun nullUsername() {
        try {
            userDto.username = null
            val saveUserService = SaveUserService(userRepository, userDtoAssembler, userDto)
            saveUserService.execute()
            fail("Username can´t be null")
        } catch (e: IllegalArgumentException) {
            assertEquals(e.message, ExceptionMessagesUser.INVALID_DATA_USER)
        }
    }

    /**
     * Checks the functionality with valid user except username is empty
     */
    @Test
    fun emptyUsername() {
        try {
            userDto.username = ""
            val saveUserService = SaveUserService(userRepository, userDtoAssembler, userDto)
            saveUserService.execute()
            fail("Username can´t be empty")
        } catch (e: IllegalArgumentException) {
            assertEquals(e.message, ExceptionMessagesUser.INVALID_DATA_USER)
        }
    }

    /**
     * Checks the functionality with valid user except password is null
     */
    @Test
    fun nullPassword() {
        try {
            userDto.password = null
            val saveUserService = SaveUserService(userRepository, userDtoAssembler, userDto)
            saveUserService.execute()
            fail("Password can´t be null")
        } catch (e: IllegalArgumentException) {
            assertEquals(e.message, ExceptionMessagesUser.INVALID_DATA_USER)
        }
    }

    /**
     * Checks the functionality with valid user except password is empty
     */
    @Test
    fun emptyPassword() {
        try {
            userDto.password = ""
            val saveUserService = SaveUserService(userRepository, userDtoAssembler, userDto)
            saveUserService.execute()
            fail("Password can´t be empty")
        } catch (e: IllegalArgumentException) {
            assertEquals(e.message, ExceptionMessagesUser.INVALID_DATA_USER)
        }
    }
}