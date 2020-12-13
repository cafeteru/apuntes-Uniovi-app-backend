package es.uniovi.apuntesuniovi.services.users

import es.uniovi.apuntesuniovi.models.User
import es.uniovi.apuntesuniovi.infrastructure.messages.UserMessages
import es.uniovi.apuntesuniovi.mocks.entities.MockUserCreator
import es.uniovi.apuntesuniovi.repositories.AddressRepository
import es.uniovi.apuntesuniovi.repositories.UserRepository
import es.uniovi.apuntesuniovi.services.commands.users.CreateUserService
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
    private val encoder = BCryptPasswordEncoder()

    @Mock
    private lateinit var userRepository: UserRepository

    @Mock
    private lateinit var addressRepository: AddressRepository

    /**
     * Create init data for the test
     */
    @BeforeEach
    fun initTest() {
        user = MockUserCreator().create()
        user.password = encoder.encode(user.password)
    }

    /**
     * Checks the functionality with valid user
     */
    @Test
    fun validUser() {
        Mockito.`when`(userRepository.save(Mockito.any(User::class.java))).thenReturn(user)
        val saveUserService = CreateUserService(userRepository, addressRepository, user)
        val result = saveUserService.execute()
        assertNotNull(result)
        assertEquals(result.password, user.password)
    }

    /**
     * Checks the functionality with valid user but the user already register
     */
    @Test
    fun existedUser() {
        Mockito.`when`(userRepository.findByUsername(user.username!!)).thenReturn(Optional.of(user))
        try {
            val saveUserService = CreateUserService(userRepository, addressRepository, user)
            saveUserService.execute()
            fail("User already registered")
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
            user.username = null
            val saveUserService = CreateUserService(userRepository, addressRepository, user)
            saveUserService.execute()
            fail("Username can´t be null")
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
            user.username = ""
            val saveUserService = CreateUserService(userRepository, addressRepository, user)
            saveUserService.execute()
            fail("Username can´t be empty")
        } catch (e: IllegalArgumentException) {
            assertEquals(e.message, UserMessages.LIMIT_USERNAME)
        }
    }

    /**
     * Checks the functionality with valid user except password is null
     */
    @Test
    fun nullPassword() {
        try {
            user.password = null
            val saveUserService = CreateUserService(userRepository, addressRepository, user)
            saveUserService.execute()
            fail("Password can´t be null")
        } catch (e: IllegalArgumentException) {
            assertEquals(e.message, UserMessages.INVALID_DATA_USER)
        }
    }

    /**
     * Checks the functionality with valid user except password is empty
     */
    @Test
    fun emptyPassword() {
        try {
            user.password = ""
            val saveUserService = CreateUserService(userRepository, addressRepository, user)
            saveUserService.execute()
            fail("Password can´t be empty")
        } catch (e: IllegalArgumentException) {
            assertEquals(e.message, UserMessages.LIMIT_PASSWORD)
        }
    }
}