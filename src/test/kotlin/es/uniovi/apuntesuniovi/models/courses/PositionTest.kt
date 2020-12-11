package es.uniovi.apuntesuniovi.models.courses

import es.uniovi.apuntesuniovi.infrastructure.constants.database.CourseLimits
import es.uniovi.apuntesuniovi.infrastructure.messages.CourseMessages
import es.uniovi.apuntesuniovi.mocks.entities.MockCourseCreator
import es.uniovi.apuntesuniovi.models.Course
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.fail
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertNull

/**
 * Test the assignments to position of a Course
 */
class PositionTest {
    private lateinit var course: Course

    /**
     * Create init data for the test
     */
    @BeforeEach
    fun initData() {
        course = MockCourseCreator().create()
    }

    /**
     * Checks the assignment under the limit
     */
    @Test
    fun limitPosition() {
        course.position = CourseLimits.POSITION_MIN
        assertEquals(CourseLimits.POSITION_MIN, course.position)
    }

    /**
     * Checks the assignment under the limit
     */
    @Test
    fun downLimitPosition() {
        try {
            course.position = CourseLimits.POSITION_MIN - 1
            fail("Position is too low")
        } catch (e: IllegalArgumentException) {
            assertEquals(e.message, CourseMessages.LIMIT_POSITION_MIN)
        }
    }

    /**
     * Checks the assignment to null
     */
    @Test
    fun nullPosition() {
        course.position = null
        assertNull(course.position)
    }
}