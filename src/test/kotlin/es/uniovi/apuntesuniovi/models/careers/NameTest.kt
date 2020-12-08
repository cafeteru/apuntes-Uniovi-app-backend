package es.uniovi.apuntesuniovi.models.careers

import es.uniovi.apuntesuniovi.infrastructure.constants.database.CareerLimits
import es.uniovi.apuntesuniovi.infrastructure.messages.CareerMessages
import es.uniovi.apuntesuniovi.mocks.entities.MockCareerCreator
import es.uniovi.apuntesuniovi.models.Career
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.fail
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/**
 * Test the assignments to name of a Career
 */
class NameTest {
    private lateinit var career: Career

    /**
     * Create init data for the test
     */
    @BeforeEach
    fun initData() {
        career = MockCareerCreator().create()
    }

    /**
     * Checks the assignment under the limit
     */
    @Test
    fun limitName() {
        var name = ""
        for (i in 0 until CareerLimits.NAME) {
            name += "1"
        }
        career.name = name
        assertEquals(name, career.name)
    }

    /**
     * Checks the assignment over the limit
     */
    @Test
    fun upLimitName() {
        try {
            var name = ""
            for (i in 0..CareerLimits.NAME) {
                name += "1"
            }
            career.name = name
            fail("Name is too big")
        } catch (e: IllegalArgumentException) {
            assertEquals(e.message, CareerMessages.LIMIT_NAME)
        }
    }

    /**
     * Checks the assignment to empty
     */
    @Test
    fun emptyName() {
        try {
            career.name = ""
            fail("Name can´t be empty")
        } catch (e: IllegalArgumentException) {
            assertEquals(e.message, CareerMessages.LIMIT_NAME)
        }
    }
}