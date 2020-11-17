package es.uniovi.apuntesuniovi.validators

import es.uniovi.apuntesuniovi.validators.impl.ValidatorMaxSize
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class ValidatorMaxSizeTest {
    @Test
    fun validData() {
        val validator = ValidatorMaxSize("uo239795", 10)
        assertTrue(validator.isValid())
    }

    @Test
    fun nullData() {
        val validator = ValidatorMaxSize(null, 10)
        assertTrue(validator.isValid())
    }

    @Test
    fun emptyData() {
        val validator = ValidatorMaxSize("", 10)
        assertTrue(validator.isValid())
    }

    @Test
    fun invalidData() {
        val validator = ValidatorMaxSize("uo239795", 6)
        assertFalse(validator.isValid())
    }
}