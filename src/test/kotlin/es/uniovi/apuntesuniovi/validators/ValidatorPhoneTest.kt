package es.uniovi.apuntesuniovi.validators

import es.uniovi.apuntesuniovi.validators.impl.ValidatorPhone
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

/**
 * Check class ValidatorPhone
 */
class ValidatorPhoneTest {

    /**
     * Checks the functionality with valid data
     */
    @Test
    fun validData() {
        var validator = ValidatorPhone("625789535")
        Assertions.assertTrue(validator.isValid())
        validator = ValidatorPhone("725789535")
        Assertions.assertTrue(validator.isValid())
        validator = ValidatorPhone("925789535")
        Assertions.assertTrue(validator.isValid())
    }

    /**
     * Checks the functionality with null data
     */
    @Test
    fun nullData() {
        val validator = ValidatorPhone(null)
        Assertions.assertTrue(validator.isValid())
    }

    /**
     * Checks the functionality with empty data
     */
    @Test
    fun emptyData() {
        val validator = ValidatorPhone("")
        Assertions.assertFalse(validator.isValid())
    }

    /**
     * Checks the functionality with invalid data
     */
    @Test
    fun invalidData() {
        var validator = ValidatorPhone("@uniovi.es")
        Assertions.assertFalse(validator.isValid())
        validator = ValidatorPhone("62578955")
        Assertions.assertFalse(validator.isValid())
        validator = ValidatorPhone("6257895533")
        Assertions.assertFalse(validator.isValid())
    }
}