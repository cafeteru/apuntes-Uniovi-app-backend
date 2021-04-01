package es.uniovi.apuntesuniovi.models.subjects

import es.uniovi.apuntesuniovi.infrastructure.constants.database.SubjectLimits
import es.uniovi.apuntesuniovi.infrastructure.messages.SubjectMessages
import es.uniovi.apuntesuniovi.mocks.entities.MockSubjectCreator
import es.uniovi.apuntesuniovi.models.Subject
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.fail
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/**
 * Test the assignments to name of a Subject
 */
class NameTest {
    private lateinit var subject: Subject

    /**
     * Create init data for the test
     */
    @BeforeEach
    fun initData() {
        subject = MockSubjectCreator().create()
    }

    /**
     * Checks the assignment under the limit
     */
    @Test
    fun limitName() {
        var name = ""
        for (i in 0 until SubjectLimits.NAME) {
            name += "1"
        }
        subject.name = name
        assertEquals(name, subject.name)
    }

    /**
     * Checks the assignment over the limit
     */
    @Test
    fun upLimitName() {
        try {
            var name = ""
            for (i in 0..SubjectLimits.NAME) {
                name += "1"
            }
            subject.name = name
            fail("Name is too big")
        } catch (e: IllegalArgumentException) {
            assertEquals(e.message, SubjectMessages.LIMIT_NAME)
        }
    }

    /**
     * Checks the assignment to empty
     */
    @Test
    fun emptyName() {
        try {
            subject.name = ""
            fail("Name can´t be empty")
        } catch (e: IllegalArgumentException) {
            assertEquals(e.message, SubjectMessages.LIMIT_NAME)
        }
    }
}