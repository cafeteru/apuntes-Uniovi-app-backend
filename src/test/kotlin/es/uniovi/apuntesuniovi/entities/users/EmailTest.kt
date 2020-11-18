package es.uniovi.apuntesuniovi.entities.users

import es.uniovi.apuntesuniovi.entities.User
import es.uniovi.apuntesuniovi.infrastructure.constants.DatabaseLimits
import es.uniovi.apuntesuniovi.infrastructure.constants.ExceptionMessages
import es.uniovi.apuntesuniovi.mocks.MockFactory
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.fail
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class EmailTest {
    private lateinit var user: User

    @BeforeEach
    fun initData() {
        user = MockFactory().getEntities().createUser()
    }

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
            assertEquals(e.message, ExceptionMessages.INVALID_EMAIL)
        }
    }

    @Test
    fun nullEmail() {
        user.email = null
        assertEquals(null, user.email)
    }
}