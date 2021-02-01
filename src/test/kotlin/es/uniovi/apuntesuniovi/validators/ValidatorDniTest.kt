package es.uniovi.apuntesuniovi.validators

import es.uniovi.apuntesuniovi.validators.impl.ValidatorDni
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

/**
 * Check class ValidatorDni
 */
class ValidatorDniTest {

  /**
   * Checks the functionality with valid data
   */
  @Test
  fun validData() {
    val validator = ValidatorDni("60692963B")
    assertTrue(validator.isValid())
  }

  /**
   * Checks the functionality with null data
   */
  @Test
  fun nullData() {
    val validator = ValidatorDni(null)
    assertTrue(validator.isValid())
  }

  /**
   * Checks the functionality with incomplete data
   */
  @Test
  fun incompleteData() {
    val validator = ValidatorDni("6069263B")
    assertFalse(validator.isValid())
  }

  /**
   * Checks the functionality with empty data
   */
  @Test
  fun emptyData() {
    val validator = ValidatorDni("")
    assertFalse(validator.isValid())
  }

  /**
   * Checks the functionality with wrong data
   */
  @Test
  fun wrongData() {
    val validator = ValidatorDni("60692Z63B")
    assertFalse(validator.isValid())
  }
}