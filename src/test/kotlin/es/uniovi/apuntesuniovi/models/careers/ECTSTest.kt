package es.uniovi.apuntesuniovi.models.careers

import es.uniovi.apuntesuniovi.infrastructure.constants.database.CareerLimits
import es.uniovi.apuntesuniovi.infrastructure.messages.CareerMessages
import es.uniovi.apuntesuniovi.mocks.entities.MockCareerCreator
import es.uniovi.apuntesuniovi.models.Career
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.fail
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertNull

/**
 * Test the assignments to ETCS of a Career
 */
class ECTSTest {
  private lateinit var career: Career

  /**
   * Create init data for the test
   */
  @BeforeEach
  fun initData() {
    career = MockCareerCreator().create()
  }

  /**
   * Checks the assignment under the limit
   */
  @Test
  fun limitECTS() {
    career.etcs = CareerLimits.ECTS_MIN
    assertEquals(CareerLimits.ECTS_MIN, career.etcs)
    career.etcs = CareerLimits.ECTS_MAX
    assertEquals(CareerLimits.ECTS_MAX, career.etcs)
  }

  /**
   * Checks the assignment under the limit
   */
  @Test
  fun downLimitECTS() {
    try {
      career.etcs = CareerLimits.ECTS_MIN - 1
      fail("ECTS is too small")
    } catch (e: IllegalArgumentException) {
      assertEquals(e.message, CareerMessages.LIMIT_ETCS_MIN)
    }
  }

  /**
   * Checks the assignment over the limit
   */
  @Test
  fun upLimitECTS() {
    try {
      career.etcs = CareerLimits.ECTS_MAX + 1
      fail("ECTS is too big")
    } catch (e: IllegalArgumentException) {
      assertEquals(e.message, CareerMessages.LIMIT_ETCS_MAX)
    }
  }

  /**
   * Checks the assignment to null
   */
  @Test
  fun nullETCS() {
    career.etcs = null
    assertNull(career.etcs)
  }
}