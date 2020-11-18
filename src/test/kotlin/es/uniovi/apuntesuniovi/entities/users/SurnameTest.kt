package es.uniovi.apuntesuniovi.entities.users

import es.uniovi.apuntesuniovi.entities.User
import es.uniovi.apuntesuniovi.infrastructure.constants.DatabaseLimits
import es.uniovi.apuntesuniovi.infrastructure.constants.ExceptionMessages
import es.uniovi.apuntesuniovi.mocks.MockFactory
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.fail
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class SurnameTest {
    private lateinit var user: User

    /**
     * Create init data for the test
     */
    @BeforeEach
    fun initData() {
        user = MockFactory().getEntities().createUser()
    }

    @Test
    fun limitSurname() {
        var surname = ""
        for (i in 0 until DatabaseLimits.USER_SURNAME) {
            surname += "1"
        }
        user.surname = surname
        assertEquals(surname, user.surname)
    }

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
            assertEquals(e.message, ExceptionMessages.LIMIT_USER_SURNAME)
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
        user.surname = ""
        assertEquals("", user.surname)
    }
}