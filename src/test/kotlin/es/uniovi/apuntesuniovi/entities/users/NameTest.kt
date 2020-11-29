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
 * Test the assignments to name of a user
 */
class NameTest {
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
    fun limitName() {
        var name = ""
        for (i in 0 until DatabaseLimits.USER_NAME) {
            name += "1"
        }
        user.name = name
        assertEquals(name, user.name)
    }

    /**
     * Checks the assignment over the limit
     */
    @Test
    fun upLimitName() {
        try {
            var name = ""
            for (i in 0..DatabaseLimits.USER_NAME) {
                name += "1"
            }
            user.name = name
            fail("Name is too big")
        } catch (e: IllegalArgumentException) {
            assertEquals(e.message, ExceptionMessagesUser.LIMIT_USER_NAME)
        }
    }

    /**
     * Checks the assignment to null
     */
    @Test
    fun nullName() {
        user.name = null
        assertEquals(null, user.name)
    }

    /**
     * Checks the assignment to empty
     */
    @Test
    fun emptyName() {
        try {
            user.name = ""
            fail("Name can´t be empty")
        } catch (e: IllegalArgumentException) {
            assertEquals(e.message, ExceptionMessagesUser.LIMIT_USER_NAME)
        }
    }
}