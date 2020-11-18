package es.uniovi.apuntesuniovi.entities.users

import es.uniovi.apuntesuniovi.entities.User
import es.uniovi.apuntesuniovi.infrastructure.constants.DatabaseLimits
import es.uniovi.apuntesuniovi.infrastructure.constants.ExceptionMessages
import es.uniovi.apuntesuniovi.mocks.MockFactory
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.fail
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PasswordTest {
    private lateinit var user: User

    @BeforeEach
    fun initData() {
        user = MockFactory().getEntities().createUser()
    }

    @Test
    fun limitPassword() {
        var password = ""
        for (i in 0 until DatabaseLimits.USER_PASSWORD) {
            password += "1"
        }
        user.password = password
        assertEquals(password, user.password)
    }

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
            assertEquals(e.message, ExceptionMessages.LIMIT_USER_PASSWORD)
        }
    }

    @Test
    fun nullPassword() {
        user.password = null
        assertEquals(null, user.password)
    }
}