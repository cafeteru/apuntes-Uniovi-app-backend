package es.uniovi.apuntesuniovi.validators

import es.uniovi.apuntesuniovi.validators.impl.ValidatorNie
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class ValidatorNieTest {

    @Test
    fun validData() {
        val validator = ValidatorNie("Z7905351A")
        assertTrue(validator.isValid())
    }

    @Test
    fun nullData() {
        val validator = ValidatorNie(null)
        assertTrue(validator.isValid())
    }

    @Test
    fun incompleteData() {
        val validator = ValidatorNie("6069263B")
        assertFalse(validator.isValid())
    }

    @Test
    fun emptyData() {
        val validator = ValidatorNie("")
        assertFalse(validator.isValid())
    }

    @Test
    fun wrongData() {
        var validator = ValidatorNie("60692Z63B")
        assertFalse(validator.isValid())
        validator = ValidatorNie("60692E63B")
        assertFalse(validator.isValid())
    }
}