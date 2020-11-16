package es.uniovi.apuntesuniovi.validators

import es.uniovi.apuntesuniovi.validators.impl.ValidatorEmail
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class ValidatorEmailTest {
    @Test
    fun validData() {
        val validator = ValidatorEmail("uo239795@uniovi.es")
        Assertions.assertTrue(validator.isValid())
    }

    @Test
    fun nullData() {
        val validator = ValidatorEmail(null)
        Assertions.assertTrue(validator.isValid())
    }

    @Test
    fun emptyData() {
        val validator = ValidatorEmail("")
        Assertions.assertFalse(validator.isValid())
    }

    @Test
    fun invalidData() {
        var validator = ValidatorEmail("@uniovi.es")
        Assertions.assertFalse(validator.isValid())
        validator = ValidatorEmail("uo239795@uniovi")
        Assertions.assertFalse(validator.isValid())
        validator = ValidatorEmail("uo239795@@uniovi")
        Assertions.assertFalse(validator.isValid())
        validator = ValidatorEmail("uo239795uniovi.es")
        Assertions.assertFalse(validator.isValid())
        validator = ValidatorEmail("uo239795uniovies")
        Assertions.assertFalse(validator.isValid())
    }
}