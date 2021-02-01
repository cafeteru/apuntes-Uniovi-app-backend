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
 * Test the assignments to yearImplantation of a Career
 */
class YearImplantationTest {
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
  fun limitYearImplantation() {
    career.yearImplantation = CareerLimits.YEAR_IMPLANTATION
    assertEquals(CareerLimits.YEAR_IMPLANTATION, career.yearImplantation)
  }

  /**
   * Checks the assignment under the limit
   */
  @Test
  fun downLimitYearImplantation() {
    try {
      career.yearImplantation = CareerLimits.YEAR_IMPLANTATION - 1
      fail("YearImplantation is too low")
    } catch (e: IllegalArgumentException) {
      assertEquals(e.message, CareerMessages.LIMIT_YEAR_IMPLANTATION)
    }
  }

  /**
   * Checks the assignment to null
   */
  @Test
  fun nullYearImplantation() {
    career.yearImplantation = null
    assertNull(career.yearImplantation)
  }
}