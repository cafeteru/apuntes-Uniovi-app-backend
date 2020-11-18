package es.uniovi.apuntesuniovi.validators

import es.uniovi.apuntesuniovi.validators.impl.ValidatorMaxLength
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class ValidatorMaxSizeTest {
    @Test
    fun validData() {
        val validator = ValidatorMaxLength("uo239795", 10)
        assertTrue(validator.isValid())
    }

    @Test
    fun nullData() {
        val validator = ValidatorMaxLength(null, 10)
        assertTrue(validator.isValid())
    }

    @Test
    fun emptyData() {
        val validator = ValidatorMaxLength("", 10)
        assertTrue(validator.isValid())
    }

    @Test
    fun invalidData() {
        val validator = ValidatorMaxLength("uo239795", 6)
        assertFalse(validator.isValid())
    }
}