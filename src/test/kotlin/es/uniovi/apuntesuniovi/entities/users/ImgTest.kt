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
 * Test the assignments to img of a user
 */
class ImgTest {
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
    fun limitImg() {
        var img = ""
        for (i in 0 until DatabaseLimits.USER_IMG) {
            img += "1"
        }
        user.img = img
        assertEquals(img, user.img)
    }

    /**
     * Checks the assignment over the limit
     */
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
            assertEquals(e.message, ExceptionMessagesUser.LIMIT_USER_IMG)
        }
    }

    /**
     * Checks the assignment to null
     */
    @Test
    fun nullImg() {
        user.img = null
        assertEquals(null, user.img)
    }

    /**
     * Checks the assignment to empty
     */
    @Test
    fun emptyImg() {
        try {
            user.img = ""
            fail("Img can´t be empty")
        } catch (e: IllegalArgumentException) {
            assertEquals(e.message, ExceptionMessagesUser.LIMIT_USER_IMG)
        }
    }
}