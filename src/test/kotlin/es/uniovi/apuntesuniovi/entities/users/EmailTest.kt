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
 * Test the assignments to email of a user
 */
class EmailTest {
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
    fun limitEmail() {
        var email = ""
        val aux = DatabaseLimits.USER_EMAIL / 2
        for (i in 1 until aux) {
            email += "1"
        }
        email += "@"
        for (i in 3 until DatabaseLimits.USER_EMAIL - aux) {
            email += "1"
        }
        email += ".es"
        user.email = email
        assertEquals(email, user.email)
    }

    /**
     * Checks the assignment over the limit
     */
    @Test
    fun upLimitEmail() {
        try {
            var email = ""
            val aux = DatabaseLimits.USER_EMAIL / 2
            for (i in 0 until aux) {
                email += "1"
            }
            email += "@"
            for (i in 3 until DatabaseLimits.USER_EMAIL - aux) {
                email += "1"
            }
            email += ".es"
            user.email = email
            assertEquals(email, user.email)
            fail("Email is too big")
        } catch (e: IllegalArgumentException) {
            assertEquals(e.message, ExceptionMessagesUser.INVALID_EMAIL)
        }
    }

    /**
     * Checks the assignment to null
     */
    @Test
    fun nullEmail() {
        user.email = null
        assertEquals(null, user.email)
    }

    /**
     * Checks the assignment to empty
     */
    @Test
    fun emptyEmail() {
        try {
            user.email = ""
            fail("Email can´t be empty")
        } catch (e: IllegalArgumentException) {
            assertEquals(e.message, ExceptionMessagesUser.INVALID_EMAIL)
        }
    }
}