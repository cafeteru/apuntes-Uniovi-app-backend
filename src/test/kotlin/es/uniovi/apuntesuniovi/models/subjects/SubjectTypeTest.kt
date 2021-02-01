package es.uniovi.apuntesuniovi.models.subjects

import es.uniovi.apuntesuniovi.infrastructure.messages.SubjectMessages
import es.uniovi.apuntesuniovi.mocks.entities.MockSubjectCreator
import es.uniovi.apuntesuniovi.models.Subject
import es.uniovi.apuntesuniovi.models.types.SubjectType
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/**
 * Test the assignments to subjectType of a Subject
 */
class SubjectTypeTest {
  private lateinit var subject: Subject
  private val subjectType = SubjectType.BASIC

  /**
   * Create init data for the test
   */
  @BeforeEach
  fun initData() {
    subject = MockSubjectCreator().create()
    subject.subjectType = subjectType
  }

  /**
   * Checks the assignment with valid data
   */
  @Test
  fun validSubjectType() {
    subject.setSubjectType(SubjectType.OPTIONAL.toString())
    assertNotEquals(subjectType, subject.subjectType)
    assertEquals(SubjectType.OPTIONAL, subject.subjectType)
  }

  /**
   * Checks the assignment with invalid data
   */
  @Test
  fun invalidSubjectType() {
    try {
      subject.setSubjectType("No exists")
      fail("SubjectType is invalid")
    } catch (e: IllegalArgumentException) {
      assertEquals(subjectType, subject.subjectType)
      assertEquals(e.message, SubjectMessages.INVALID_SUBJECT_TYPE)
    }
  }

  /**
   * Checks the assignment to null
   */
  @Test
  fun nullSubjectType() {
    subject.setSubjectType(null)
    assertNull(subject.subjectType)
  }

  /**
   * Checks the assignment to empty
   */
  @Test
  fun emptySubjectType() {
    try {
      subject.setSubjectType("")
      fail("SubjectType is empty")
    } catch (e: IllegalArgumentException) {
      assertEquals(subjectType, subject.subjectType)
      assertEquals(e.message, SubjectMessages.INVALID_SUBJECT_TYPE)
    }
  }
}