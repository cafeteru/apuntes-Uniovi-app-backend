package es.uniovi.apuntesuniovi.models.semesters

import es.uniovi.apuntesuniovi.infrastructure.constants.database.SemesterLimits
import es.uniovi.apuntesuniovi.infrastructure.messages.SemesterMessages
import es.uniovi.apuntesuniovi.mocks.entities.MockSemesterCreator
import es.uniovi.apuntesuniovi.models.Semester
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.fail
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertNull

/**
 * Test the assignments to position of a Semester
 */
class PositionTest {
    private lateinit var semester: Semester

    /**
     * Create init data for the test
     */
    @BeforeEach
    fun initData() {
        semester = MockSemesterCreator().create()
    }

    /**
     * Checks the assignment under the limit
     */
    @Test
    fun limitPosition() {
        semester.position = SemesterLimits.POSITION_MIN
        assertEquals(SemesterLimits.POSITION_MIN, semester.position)
    }

    /**
     * Checks the assignment under the limit
     */
    @Test
    fun downLimitPosition() {
        try {
            semester.position = SemesterLimits.POSITION_MIN - 1
            fail("Position is too low")
        } catch (e: IllegalArgumentException) {
            assertEquals(e.message, SemesterMessages.LIMIT_POSITION_MIN)
        }
    }

    /**
     * Checks the assignment to null
     */
    @Test
    fun nullPosition() {
        semester.position = null
        assertNull(semester.position)
    }
}