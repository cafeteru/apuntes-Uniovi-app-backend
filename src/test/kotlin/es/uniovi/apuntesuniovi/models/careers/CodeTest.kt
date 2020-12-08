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
 * Test the assignments to code of a Career
 */
class CodeTest {
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
    fun limitCode() {
        var code = ""
        for (i in 0 until CareerLimits.CODE) {
            code += "1"
        }
        career.code = code
        assertEquals(code, career.code)
    }

    /**
     * Checks the assignment over the limit
     */
    @Test
    fun upLimitCode() {
        try {
            var code = ""
            for (i in 0..CareerLimits.CODE) {
                code += "1"
            }
            career.code = code
            fail("Code is too big")
        } catch (e: IllegalArgumentException) {
            assertEquals(e.message, CareerMessages.LIMIT_CODE)
        }
    }

    /**
     * Checks the assignment to empty
     */
    @Test
    fun emptyCode() {
        try {
            career.code = ""
            fail("Code can´t be empty")
        } catch (e: IllegalArgumentException) {
            assertEquals(e.message, CareerMessages.LIMIT_CODE)
        }
    }
}