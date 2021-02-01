package es.uniovi.apuntesuniovi.validators

import es.uniovi.apuntesuniovi.validators.impl.ValidatorId
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

/**
 * Check class ValidatorId
 */
class ValidatorIdTest {

  /**
   * Checks the functionality with valid data
   */
  @Test
  fun validData() {
    val validator = ValidatorId(1)
    assertTrue(validator.isValid())
  }

  /**
   * Checks the functionality with null data
   */
  @Test
  fun nullData() {
    val validator = ValidatorId(null)
    assertFalse(validator.isValid())
  }

  /**
   * Checks the functionality with invalid data
   */
  @Test
  fun invalidData() {
    var validator = ValidatorId(0)
    assertFalse(validator.isValid())
    validator = ValidatorId(-1)
    assertFalse(validator.isValid())
  }
}