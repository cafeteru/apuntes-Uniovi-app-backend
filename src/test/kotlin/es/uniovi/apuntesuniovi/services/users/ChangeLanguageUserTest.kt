package es.uniovi.apuntesuniovi.services.users

import es.uniovi.apuntesuniovi.infrastructure.messages.UserMessages
import es.uniovi.apuntesuniovi.mocks.entities.MockUserCreator
import es.uniovi.apuntesuniovi.models.types.LanguageType
import es.uniovi.apuntesuniovi.repositories.AddressRepository
import es.uniovi.apuntesuniovi.repositories.UserRepository
import es.uniovi.apuntesuniovi.services.UserService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.fail

/**
 * Check the change of language method of the UserService class
 */
@ExtendWith(MockitoExtension::class)
class ChangeLanguageUserTest {
    private lateinit var userService: UserService

    @Mock
    private lateinit var userRepository: UserRepository

    @Mock
    private lateinit var addressRepository: AddressRepository
    private val username = "username"

    /**
     * Create init data for the test
     */
    @BeforeEach
    fun initTest() {
        userService = UserService(userRepository, addressRepository)
    }

    /**
     * Checks with valid data
     */
    @Test
    fun validData() {
        val user = MockUserCreator().create()
        user.username = username
        Mockito.`when`(userRepository.findByUsername(username)).thenReturn(Optional.of(user))
        val result = userService.changeLanguage(username, LanguageType.ES.toString())
        assertTrue(result)
    }

    /**
     * Check with invalid language
     */
    @Test
    fun invalidLanguage() {
        val user = MockUserCreator().create()
        user.username = username
        Mockito.`when`(userRepository.findByUsername(username)).thenReturn(Optional.of(user))
        try {
            userService.changeLanguage(username, "")
            fail(UserMessages.INVALID_LANGUAGE)
        } catch (e: IllegalArgumentException) {
            assertEquals(e.message, UserMessages.INVALID_LANGUAGE)
        }
    }

    /**
     * Check with invalid username
     */
    @Test
    fun invalidUsername() {
        val user = MockUserCreator().create()
        user.username = username
        try {
            userService.changeLanguage(username, "")
            fail(UserMessages.NOT_FOUND)
        } catch (e: IllegalArgumentException) {
            assertEquals(e.message, UserMessages.NOT_FOUND)
        }
    }
}