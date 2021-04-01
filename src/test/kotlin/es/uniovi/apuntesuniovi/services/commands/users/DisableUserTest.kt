package es.uniovi.apuntesuniovi.services.commands.users

import es.uniovi.apuntesuniovi.infrastructure.messages.UserMessages
import es.uniovi.apuntesuniovi.mocks.entities.MockUserCreator
import es.uniovi.apuntesuniovi.models.User
import es.uniovi.apuntesuniovi.repositories.UserRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.fail

/**
 * Check class DisableUser
 */
@ExtendWith(MockitoExtension::class)
class DisableUserTest {
    private lateinit var user: User

    @Mock
    private lateinit var userRepository: UserRepository

    /**
     * Create init data for the test
     */
    @BeforeEach
    fun initTest() {
        user = MockUserCreator().create()
    }

    /**
     * Checks with valid id and existing user
     */
    @Test
    fun validIdAndExistUser() {
        Mockito.`when`(userRepository.findById(user.id!!)).thenReturn(Optional.of(user))
        Mockito.`when`(userRepository.save(user)).thenReturn(user)
        val disabledUser = DisableUser(userRepository, user.id!!, true)
        val result = disabledUser.execute()
        Assertions.assertNotNull(result)
        assertEquals(user, result)
    }

    /**
     * Checks with valid id and not existing user
     */
    @Test
    fun validIdAndNotExistUser() {
        try {
            DisableUser(userRepository, 1L, true).execute()
            fail("User not found")
        } catch (e: IllegalArgumentException) {
            Assertions.assertEquals(e.message, UserMessages.NOT_FOUND)
        }
    }

    /**
     * Checks with invalid id
     */
    @Test
    fun invalidId() {
        try {
            DisableUser(userRepository, -1L, true).execute()
            fail("Invalid user id")
        } catch (e: IllegalArgumentException) {
            Assertions.assertEquals(e.message, UserMessages.INVALID_ID)
        }
    }
}