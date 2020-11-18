package es.uniovi.apuntesuniovi.entities.users

import es.uniovi.apuntesuniovi.entities.User
import es.uniovi.apuntesuniovi.infrastructure.constants.DatabaseLimits
import es.uniovi.apuntesuniovi.infrastructure.constants.ExceptionMessages
import es.uniovi.apuntesuniovi.mocks.MockFactory
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.fail
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class NameTest {
    private lateinit var user: User

    @BeforeEach
    fun initData() {
        user = MockFactory().getEntities().createUser()
    }

    @Test
    fun limitName() {
        var name = ""
        for (i in 0 until DatabaseLimits.USER_NAME) {
            name += "1"
        }
        user.name = name
        assertEquals(name, user.name)
    }

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
            assertEquals(e.message, ExceptionMessages.LIMIT_USER_NAME)
        }
    }

    @Test
    fun nullName() {
        user.name = null
        assertEquals(null, user.name)
    }
}