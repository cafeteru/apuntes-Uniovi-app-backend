package es.uniovi.apuntesuniovi.models.optionEtcs

import es.uniovi.apuntesuniovi.infrastructure.constants.database.OptionETCSLimits
import es.uniovi.apuntesuniovi.infrastructure.messages.OptionETCSMessages
import es.uniovi.apuntesuniovi.mocks.entities.MockOptionETCSCreator
import es.uniovi.apuntesuniovi.models.OptionETCS
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.fail
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/**
 * Test the assignments to etcs of a OptionETCS
 */
class EtcsTest {
  private lateinit var optionETCS: OptionETCS

  /**
   * Create init data for the test
   */
  @BeforeEach
  fun initData() {
    optionETCS = MockOptionETCSCreator().create()
  }

  /**
   * Checks the assignment under the limit
   */
  @Test
  fun limitEtcs() {
    optionETCS.etcs = OptionETCSLimits.ECTS_MIN
    assertEquals(OptionETCSLimits.ECTS_MIN, optionETCS.etcs)
    optionETCS.etcs = OptionETCSLimits.ECTS_MAX
    assertEquals(OptionETCSLimits.ECTS_MAX, optionETCS.etcs)
  }

  /**
   * Checks the assignment under the limit
   */
  @Test
  fun downLimitEtcs() {
    try {
      optionETCS.etcs = OptionETCSLimits.ECTS_MIN - 1
      fail("Etcs is too low")
    } catch (e: IllegalArgumentException) {
      assertEquals(e.message, OptionETCSMessages.LIMIT_ETCS_MIN)
    }
  }

  /**
   * Checks the assignment to null
   */
  @Test
  fun upLimitEtcs() {
    try {
      optionETCS.etcs = OptionETCSLimits.ECTS_MAX + 1
      fail("Etcs is too high")
    } catch (e: IllegalArgumentException) {
      assertEquals(e.message, OptionETCSMessages.LIMIT_ETCS_MAX)
    }
  }
}