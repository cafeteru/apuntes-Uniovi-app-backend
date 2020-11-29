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
 * Test the assignments to username of a user
 */
class UsernameTest {
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
    fun limitUsername() {
        var username = ""
        for (i in 0 until DatabaseLimits.USER_USERNAME) {
            username += "1"
        }
        user.username = username
        assertEquals(username, user.username)
    }

    /**
     * Checks the assignment over the limit
     */
    @Test
    fun upLimitUsername() {
        try {
            var username = ""
            for (i in 0..DatabaseLimits.USER_NAME) {
                username += "1"
            }
            user.username = username
            fail("Username is too big")
        } catch (e: IllegalArgumentException) {
            assertEquals(e.message, ExceptionMessagesUser.LIMIT_USER_USERNAME)
        }
    }


    /**
     * Checks the assignment to null
     */
    @Test
    fun nullUsername() {
        user.username = null
        assertEquals(null, user.username)
    }

    /**
     * Checks the assignment to empty
     */
    @Test
    fun emptyUsername() {
        try {
            user.username = ""
            fail("Username can´t be empty")
        } catch (e: IllegalArgumentException) {
            assertEquals(e.message, ExceptionMessagesUser.LIMIT_USER_USERNAME)
        }
    }
}