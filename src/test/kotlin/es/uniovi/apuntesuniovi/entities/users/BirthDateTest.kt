package es.uniovi.apuntesuniovi.entities.users

import es.uniovi.apuntesuniovi.entities.User
import es.uniovi.apuntesuniovi.infrastructure.exceptions.messages.UserMessages
import es.uniovi.apuntesuniovi.mocks.MockFactory
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.fail
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDate

/**
 * Test the assignments to birthDate of a user
 */
class BirthDateTest {
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
    fun limitBirthDate() {
        val birthDate = LocalDate.now()
        user.birthDate = birthDate
        assertEquals(birthDate, user.birthDate)
    }

    /**
     * Checks the assignment over the limit
     */
    @Test
    fun upLimitBirthDate() {
        try {
            val birthDate = LocalDate.now().plusDays(1)
            user.birthDate = birthDate
            fail("BirthDate is too big")
        } catch (e: IllegalArgumentException) {
            assertEquals(e.message, UserMessages.LIMIT_BIRTH_DATE)
        }
    }

    /**
     * Checks the assignment to null
     */
    @Test
    fun nullBirthDate() {
        user.birthDate = null
        assertEquals(null, user.birthDate)
    }
}