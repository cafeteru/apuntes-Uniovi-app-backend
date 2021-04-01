package es.uniovi.apuntesuniovi.models.users

import es.uniovi.apuntesuniovi.infrastructure.messages.UserMessages
import es.uniovi.apuntesuniovi.mocks.entities.MockUserCreator
import es.uniovi.apuntesuniovi.models.User
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/**
 * Test the assignments to number of identification of a user
 */
class NumberIdentificationTest {
    private lateinit var user: User

    /**
     * Create init data for the test
     */
    @BeforeEach
    fun initData() {
        user = MockUserCreator().create()
    }

    /**
     * Checks the assignment with valid data
     */
    @Test
    fun validNumberIdentification() {
        val numberIdentification = "65057750L"
        user.numberIdentification = numberIdentification
        assertEquals(numberIdentification, user.numberIdentification)
    }

    /**
     * Checks the assignment with invalid data
     */
    @Test
    fun invalidNumberIdentification() {
        val numberIdentification = "T8057750L"
        try {
            user.numberIdentification = numberIdentification
            fail("NumberIdentification isn´t valid")
        } catch (e: java.lang.IllegalArgumentException) {
            assertNotEquals(numberIdentification, user.numberIdentification)
            assertEquals(e.message, UserMessages.INVALID_IDENTIFICATION_NUMBER)
        }
    }

    /**
     * Checks the assignment to null
     */
    @Test
    fun nullNumberIdentification() {
        user.numberIdentification = null
        assertEquals(null, user.numberIdentification)
    }

    /**
     * Checks the assignment to empty
     */
    @Test
    fun emptyNumberIdentification() {
        try {
            user.numberIdentification = ""
            fail("NumberIdentification can´t be empty")
        } catch (e: IllegalArgumentException) {
            assertEquals(e.message, UserMessages.INVALID_IDENTIFICATION_NUMBER)
        }
    }
}