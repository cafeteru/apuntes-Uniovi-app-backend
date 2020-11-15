package es.uniovi.apuntesuniovi.validators

import es.uniovi.apuntesuniovi.validators.impl.ValidatorDni
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class ValidatorDniTest {

    @Test
    fun validData() {
        val validator = ValidatorDni("60692963B")
        assertTrue(validator.isValid())
    }

    @Test
    fun nullData() {
        val validator = ValidatorDni(null)
        assertTrue(validator.isValid())
    }

    @Test
    fun incompleteData() {
        val validator = ValidatorDni("6069263B")
        assertFalse(validator.isValid())
    }

    @Test
    fun emptyData() {
        val validator = ValidatorDni("")
        assertFalse(validator.isValid())
    }

    @Test
    fun wrongData() {
        val validator = ValidatorDni("60692Z63B")
        assertFalse(validator.isValid())
    }
}