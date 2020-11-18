package es.uniovi.apuntesuniovi.validators

import es.uniovi.apuntesuniovi.validators.impl.ValidatorNie
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

/**
 * Check class ValidatorNie
 */
class ValidatorNieTest {

    /**
     * Checks the functionality with valid data
     */
    @Test
    fun validData() {
        val validator = ValidatorNie("Z7905351A")
        assertTrue(validator.isValid())
    }

    /**
     * Checks the functionality with null data
     */
    @Test
    fun nullData() {
        val validator = ValidatorNie(null)
        assertTrue(validator.isValid())
    }

    /**
     * Checks the functionality with incomplete data
     */
    @Test
    fun incompleteData() {
        val validator = ValidatorNie("6069263B")
        assertFalse(validator.isValid())
    }

    /**
     * Checks the functionality with empty data
     */
    @Test
    fun emptyData() {
        val validator = ValidatorNie("")
        assertFalse(validator.isValid())
    }

    /**
     * Checks the functionality with wrong data
     */
    @Test
    fun wrongData() {
        var validator = ValidatorNie("60692Z63B")
        assertFalse(validator.isValid())
        validator = ValidatorNie("60692E63B")
        assertFalse(validator.isValid())
    }
}