package es.uniovi.apuntesuniovi.entities.centers

import es.uniovi.apuntesuniovi.entities.Center
import es.uniovi.apuntesuniovi.infrastructure.constants.database.CenterLimits
import es.uniovi.apuntesuniovi.infrastructure.messages.CenterMessages
import es.uniovi.apuntesuniovi.mocks.entities.MockCenterCreator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.fail
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/**
 * Test the assignments to name of a center
 */
class NameTest {
    private lateinit var center: Center

    /**
     * Create init data for the test
     */
    @BeforeEach
    fun initData() {
        center = MockCenterCreator().create()
    }

    /**
     * Checks the assignment under the limit
     */
    @Test
    fun limitName() {
        var name = ""
        for (i in 0 until CenterLimits.NAME) {
            name += "1"
        }
        center.name = name
        assertEquals(name, center.name)
    }

    /**
     * Checks the assignment over the limit
     */
    @Test
    fun upLimitName() {
        try {
            var name = ""
            for (i in 0..CenterLimits.NAME) {
                name += "1"
            }
            center.name = name
            fail("Name is too big")
        } catch (e: IllegalArgumentException) {
            assertEquals(e.message, CenterMessages.LIMIT_NAME)
        }
    }

    /**
     * Checks the assignment to empty
     */
    @Test
    fun emptyName() {
        try {
            center.name = ""
            fail("Name can´t be empty")
        } catch (e: IllegalArgumentException) {
            assertEquals(e.message, CenterMessages.LIMIT_NAME)
        }
    }
}