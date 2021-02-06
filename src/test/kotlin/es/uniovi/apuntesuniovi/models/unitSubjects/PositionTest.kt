package es.uniovi.apuntesuniovi.models.unitSubjects

import es.uniovi.apuntesuniovi.infrastructure.constants.database.UnitSubjectLimits
import es.uniovi.apuntesuniovi.infrastructure.messages.UnitSubjectMessages
import es.uniovi.apuntesuniovi.mocks.entities.MockUnitSubjectCreator
import es.uniovi.apuntesuniovi.models.UnitSubject
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.fail
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertNull

/**
 * Test the assignments to position of a UnitSubject
 */
class PositionTest {
  private lateinit var unitSubject: UnitSubject

  /**
   * Create init data for the test
   */
  @BeforeEach
  fun initData() {
    unitSubject = MockUnitSubjectCreator().create()
  }

  /**
   * Checks the assignment under the limit
   */
  @Test
  fun limitPosition() {
    unitSubject.position = UnitSubjectLimits.POSITION_MIN
    assertEquals(UnitSubjectLimits.POSITION_MIN, unitSubject.position)
  }

  /**
   * Checks the assignment under the limit
   */
  @Test
  fun downLimitPosition() {
    try {
      unitSubject.position = UnitSubjectLimits.POSITION_MIN - 1
      fail("Position is too low")
    } catch (e: IllegalArgumentException) {
      assertEquals(e.message, UnitSubjectMessages.LIMIT_POSITION_MIN)
    }
  }

  /**
   * Checks the assignment to null
   */
  @Test
  fun nullPosition() {
    unitSubject.position = null
    assertNull(unitSubject.position)
  }
}