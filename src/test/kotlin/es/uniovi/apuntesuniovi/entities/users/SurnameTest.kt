package es.uniovi.apuntesuniovi.entities.users

import es.uniovi.apuntesuniovi.entities.User
import es.uniovi.apuntesuniovi.infrastructure.constants.DatabaseLimits
import es.uniovi.apuntesuniovi.infrastructure.exceptions.messages.UserMessages
import es.uniovi.apuntesuniovi.mocks.MockFactory
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.fail
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/**
 * Test the assignments to surname of a user
 */
class SurnameTest {
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
    fun limitSurname() {
        var surname = ""
        for (i in 0 until DatabaseLimits.USER_SURNAME) {
            surname += "1"
        }
        user.surname = surname
        assertEquals(surname, user.surname)
    }

    /**
     * Checks the assignment over the limit
     */
    @Test
    fun upLimitSurname() {
        try {
            var surname = ""
            for (i in 0..DatabaseLimits.USER_SURNAME) {
                surname += "1"
            }
            user.surname = surname
            fail("Surname is too big")
        } catch (e: IllegalArgumentException) {
            assertEquals(e.message, UserMessages.LIMIT_SURNAME)
        }
    }

    /**
     * Checks the assignment to null
     */
    @Test
    fun nullSurname() {
        user.surname = null
        assertEquals(null, user.surname)
    }

    /**
     * Checks the assignment to empty
     */
    @Test
    fun emptySurname() {
        try {
            user.surname = ""
            fail("Surname can´t be empty")
        } catch (e: IllegalArgumentException) {
            assertEquals(e.message, UserMessages.LIMIT_SURNAME)
        }
    }
}