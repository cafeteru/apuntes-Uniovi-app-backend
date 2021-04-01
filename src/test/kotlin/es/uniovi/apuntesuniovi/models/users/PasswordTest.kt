package es.uniovi.apuntesuniovi.models.users

import es.uniovi.apuntesuniovi.infrastructure.constants.database.UserLimits
import es.uniovi.apuntesuniovi.infrastructure.messages.UserMessages
import es.uniovi.apuntesuniovi.mocks.entities.MockUserCreator
import es.uniovi.apuntesuniovi.models.User
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
        user = MockUserCreator().create()
    }

    /**
     * Checks the assignment under the limit
     */
    @Test
    fun limitPassword() {
        var password = ""
        for (i in 0 until UserLimits.PASSWORD) {
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
            for (i in 0..UserLimits.PASSWORD) {
                password += "1"
            }
            user.password = password
            fail("Password is too big")
        } catch (e: IllegalArgumentException) {
            assertEquals(e.message, UserMessages.LIMIT_PASSWORD)
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
            assertEquals(e.message, UserMessages.LIMIT_PASSWORD)
        }
    }
}