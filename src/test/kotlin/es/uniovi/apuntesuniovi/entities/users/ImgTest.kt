package es.uniovi.apuntesuniovi.entities.users

import es.uniovi.apuntesuniovi.entities.User
import es.uniovi.apuntesuniovi.infrastructure.constants.DatabaseLimits
import es.uniovi.apuntesuniovi.infrastructure.constants.ExceptionMessages
import es.uniovi.apuntesuniovi.mocks.MockFactory
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.fail
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ImgTest {
    private lateinit var user: User

    @BeforeEach
    fun initData() {
        user = MockFactory().getEntities().createUser()
    }

    @Test
    fun limitImg() {
        var img = ""
        for (i in 0 until DatabaseLimits.USER_IMG) {
            img += "1"
        }
        user.img = img
        assertEquals(img, user.img)
    }

    @Test
    fun upLimitImg() {
        try {
            var img = ""
            for (i in 0..DatabaseLimits.USER_IMG) {
                img += "1"
            }
            user.img = img
            fail("Img is too big")
        } catch (e: IllegalArgumentException) {
            assertEquals(e.message, ExceptionMessages.LIMIT_USER_IMG)
        }
    }

    @Test
    fun nullImg() {
        user.img = null
        assertEquals(null, user.img)
    }
}