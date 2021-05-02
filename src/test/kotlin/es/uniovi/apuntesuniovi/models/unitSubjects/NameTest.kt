package es.uniovi.apuntesuniovi.models.unitSubjects

import es.uniovi.apuntesuniovi.infrastructure.constants.database.UnitSubjectLimits
import es.uniovi.apuntesuniovi.infrastructure.messages.UnitSubjectMessages
import es.uniovi.apuntesuniovi.mocks.entities.MockUnitSubject
import es.uniovi.apuntesuniovi.models.UnitSubject
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.fail
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertNull

/**
 * Test the assignments to name of a UnitSubject
 */
class NameTest {
    private lateinit var unitSubject: UnitSubject

    /**
     * Create init data for the test
     */
    @BeforeEach
    fun initData() {
        unitSubject = MockUnitSubject().create()
    }

    /**
     * Checks the assignment under the limit
     */
    @Test
    fun limitName() {
        var name = ""
        for (i in 0 until UnitSubjectLimits.NAME) {
            name += "1"
        }
        unitSubject.name = name
        assertEquals(name, unitSubject.name)
    }

    /**
     * Checks the assignment over the limit
     */
    @Test
    fun upLimitName() {
        try {
            var name = ""
            for (i in 0..UnitSubjectLimits.NAME) {
                name += "1"
            }
            unitSubject.name = name
            fail("Name is too big")
        } catch (e: IllegalArgumentException) {
            assertEquals(e.message, UnitSubjectMessages.LIMIT_NAME)
        }
    }

    /**
     * Checks the assignment to empty
     */
    @Test
    fun emptyName() {
        try {
            unitSubject.name = ""
            fail("Name can´t be empty")
        } catch (e: IllegalArgumentException) {
            assertEquals(e.message, UnitSubjectMessages.LIMIT_NAME)
        }
    }

    /**
     * Checks the assignment to null
     */
    @Test
    fun nullName() {
        unitSubject.name = null
        assertNull(unitSubject.name)
    }
}