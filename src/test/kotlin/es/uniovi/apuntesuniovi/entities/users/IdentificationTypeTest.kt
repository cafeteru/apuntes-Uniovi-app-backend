package es.uniovi.apuntesuniovi.entities.users

import es.uniovi.apuntesuniovi.entities.User
import es.uniovi.apuntesuniovi.entities.types.IdentificationType
import es.uniovi.apuntesuniovi.infrastructure.exceptions.messages.UserMessages
import es.uniovi.apuntesuniovi.mocks.entities.MockUserCreator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.fail
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/**
 * Test the assignments to identificationType of a user
 */
class IdentificationTypeTest {
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
    fun validIdentificationType() {
        user.setIdentificationType("DNI")
        assertEquals(IdentificationType.DNI, user.identificationType)
    }

    /**
     * Checks the assignment with invalid data
     */
    @Test
    fun invalidIdentificationType() {
        try {
            user.setIdentificationType("No exists")
            fail("IdentificationType is invalid")
        } catch (e: IllegalArgumentException) {
            assertEquals(IdentificationType.NIE, user.identificationType)
            assertEquals(e.message, UserMessages.INVALID_IDENTIFICATION_TYPE)
        }
    }

    /**
     * Checks the assignment to empty
     */
    @Test
    fun emptyIdentificationType() {
        try {
            user.setIdentificationType("")
            fail("IdentificationType can´t be empty")
        } catch (e: IllegalArgumentException) {
            assertEquals(IdentificationType.NIE, user.identificationType)
            assertEquals(e.message, UserMessages.INVALID_IDENTIFICATION_TYPE)
        }
    }

    /**
     * Checks the assignment to null
     */
    @Test
    fun nullIdentificationType() {
        try {
            user.setIdentificationType(null)
            fail("IdentificationType can´t be null")
        } catch (e: IllegalArgumentException) {
            assertEquals(IdentificationType.NIE, user.identificationType)
            assertEquals(e.message, UserMessages.NULL_IDENTIFICATION_TYPE)
        }
    }
}