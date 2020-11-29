package es.uniovi.apuntesuniovi.entities.users

import es.uniovi.apuntesuniovi.entities.User
import es.uniovi.apuntesuniovi.infrastructure.exceptions.messages.ExceptionMessagesUser
import es.uniovi.apuntesuniovi.mocks.MockFactory
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/**
 * Test the assignments to phone of a user
 */
class PhoneTest {
    private lateinit var user: User

    /**
     * Create init data for the test
     */
    @BeforeEach
    fun initData() {
        user = MockFactory().getEntities().createUser()
    }

    /**
     * Checks the assignment with valid data
     */
    @Test
    fun validPhone() {
        val phone = "652789456"
        user.phone = phone
        assertEquals(phone, user.phone)
    }

    /**
     * Checks the assignment with invalid data
     */
    @Test
    fun invalidPhone() {
        val phone = "652789S456"
        try {
            user.phone = phone
            fail("Phone isn´t valid")
        } catch (e: IllegalArgumentException) {
            assertNotEquals(user.phone, phone)
            assertEquals(e.message, ExceptionMessagesUser.INVALID_PHONE)
        }
    }

    /**
     * Checks the assignment to null
     */
    @Test
    fun nullPhone() {
        user.phone = null
        assertEquals(null, user.phone)
    }

    /**
     * Checks the assignment to empty
     */
    @Test
    fun emptyPhone() {
        try {
            user.phone = ""
            fail("Phone isn´t empty")
        } catch (e: IllegalArgumentException) {
            assertNotEquals(user.phone, "")
            assertEquals(e.message, ExceptionMessagesUser.INVALID_PHONE)
        }
    }
}