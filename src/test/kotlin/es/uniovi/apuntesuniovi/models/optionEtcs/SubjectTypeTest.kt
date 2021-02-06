package es.uniovi.apuntesuniovi.models.optionEtcs

import es.uniovi.apuntesuniovi.infrastructure.messages.OptionETCSMessages
import es.uniovi.apuntesuniovi.mocks.entities.MockOptionETCSCreator
import es.uniovi.apuntesuniovi.models.OptionETCS
import es.uniovi.apuntesuniovi.models.types.SubjectType
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/**
 * Test the assignments to subjectType of a OptionETCS
 */
class SubjectTypeTest {
  private lateinit var optionETCS: OptionETCS
  private val subjectType = SubjectType.BASIC

  /**
   * Create init data for the test
   */
  @BeforeEach
  fun initData() {
    optionETCS = MockOptionETCSCreator().create()
    optionETCS.subjectType = subjectType
  }

  /**
   * Checks the assignment with valid data
   */
  @Test
  fun validSubjectType() {
    optionETCS.setSubjectType(SubjectType.OPTIONAL.toString())
    assertNotEquals(subjectType, optionETCS.subjectType)
    assertEquals(SubjectType.OPTIONAL, optionETCS.subjectType)
  }

  /**
   * Checks the assignment with invalid data
   */
  @Test
  fun invalidSubjectType() {
    try {
      optionETCS.setSubjectType("No exists")
      fail("SubjectType is invalid")
    } catch (e: IllegalArgumentException) {
      assertEquals(subjectType, optionETCS.subjectType)
      assertEquals(e.message, OptionETCSMessages.INVALID_SUBJECT_TYPE)
    }
  }

  /**
   * Checks the assignment to null
   */
  @Test
  fun nullSubjectType() {
    optionETCS.setSubjectType(null)
    assertNull(optionETCS.subjectType)
  }

  /**
   * Checks the assignment to empty
   */
  @Test
  fun emptySubjectType() {
    try {
      optionETCS.setSubjectType("")
      fail("SubjectType is empty")
    } catch (e: IllegalArgumentException) {
      assertEquals(subjectType, optionETCS.subjectType)
      assertEquals(e.message, OptionETCSMessages.INVALID_SUBJECT_TYPE)
    }
  }
}