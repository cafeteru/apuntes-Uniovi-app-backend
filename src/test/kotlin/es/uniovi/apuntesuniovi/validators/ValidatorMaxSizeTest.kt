package es.uniovi.apuntesuniovi.validators

import es.uniovi.apuntesuniovi.validators.impl.ValidatorMaxLength
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

/**
 * Check class ValidatorMaxSize
 */
class ValidatorMaxSizeTest {

    /**
     * Checks the functionality with valid data
     */
    @Test
    fun validData() {
        val validator = ValidatorMaxLength("uo239795", 10)
        assertTrue(validator.isValid())
    }

    /**
     * Checks the functionality with null data
     */
    @Test
    fun nullData() {
        val validator = ValidatorMaxLength(null, 10)
        assertTrue(validator.isValid())
    }

    /**
     * Checks the functionality with empty data
     */
    @Test
    fun emptyData() {
        val validator = ValidatorMaxLength("", 10)
        assertFalse(validator.isValid())
    }

    /**
     * Checks the functionality with invalid data
     */
    @Test
    fun invalidData() {
        val validator = ValidatorMaxLength("uo239795", 6)
        assertFalse(validator.isValid())
    }
}