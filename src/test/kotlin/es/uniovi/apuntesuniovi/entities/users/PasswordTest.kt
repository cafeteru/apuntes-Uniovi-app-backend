package es.uniovi.apuntesuniovi.entities.users

import es.uniovi.apuntesuniovi.entities.User
import es.uniovi.apuntesuniovi.infrastructure.constants.DatabaseLimits
import es.uniovi.apuntesuniovi.infrastructure.exceptions.messages.ExceptionMessagesUser
import es.uniovi.apuntesuniovi.mocks.MockFactory
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.fail
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/**
 * Test the assignments to password of a user
 */
class PasswordTest {
    private lateinit var user: User

    /**
     * Create init data for the test
     */
    @BeforeEach
    fun initData() {
        user = MockFactory().getEntities().createUser()
    }

    /**
     * Checks the assignment under the limit
     */
    @Test
    fun limitPassword() {
        var password = ""
        for (i in 0 until DatabaseLimits.USER_PASSWORD) {
            password += "1"
        }
        user.password = password
        assertEquals(password, user.password)
    }

    /**
     * Checks the assignment over the limit
     */
    @Test
    fun upLimitPassword() {
        try {
            var password = ""
            for (i in 0..DatabaseLimits.USER_PASSWORD) {
                password += "1"
            }
            user.password = password
            fail("Password is too big")
        } catch (e: IllegalArgumentException) {
            assertEquals(e.message, ExceptionMessagesUser.LIMIT_USER_PASSWORD)
        }
    }

    /**
     * Checks the assignment to null
     */
    @Test
    fun nullPassword() {
        user.password = null
        assertEquals(null, user.password)
    }

    /**
     * Checks the assignment to empty
     */
    @Test
    fun emptyPassword() {
        try {
            user.password = ""
            fail("Password can´t be empty")
        } catch (e: IllegalArgumentException) {
            assertEquals(e.message, ExceptionMessagesUser.LIMIT_USER_PASSWORD)
        }
    }
}