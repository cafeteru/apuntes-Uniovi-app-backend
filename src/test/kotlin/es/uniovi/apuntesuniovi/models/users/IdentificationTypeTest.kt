package es.uniovi.apuntesuniovi.models.users

import es.uniovi.apuntesuniovi.infrastructure.messages.UserMessages
import es.uniovi.apuntesuniovi.mocks.entities.MockUserCreator
import es.uniovi.apuntesuniovi.models.User
import es.uniovi.apuntesuniovi.models.types.IdentificationType
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.fail
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertNull

/**
 * Test the assignments to identificationType of a user
 */
class IdentificationTypeTest {
  private lateinit var user: User

  /**
   * Create init data for the test
   */
  @BeforeEach
  fun initData() {
    user = MockUserCreator().create()
  }

  /**
   * Checks the assignment with valid data
   */
  @Test
  fun validIdentificationType() {
    user.setIdentificationType(IdentificationType.DNI.toString())
    assertEquals(IdentificationType.DNI, user.identificationType)
  }

  /**
   * Checks the assignment with invalid data
   */
  @Test
  fun invalidIdentificationType() {
    try {
      user.setIdentificationType("No exists")
      fail("IdentificationType is invalid")
    } catch (e: IllegalArgumentException) {
      assertEquals(IdentificationType.DNI, user.identificationType)
      assertEquals(e.message, UserMessages.INVALID_IDENTIFICATION_TYPE)
    }
  }

  /**
   * Checks the assignment to empty
   */
  @Test
  fun emptyIdentificationType() {
    try {
      user.setIdentificationType("")
      fail("IdentificationType can´t be empty")
    } catch (e: IllegalArgumentException) {
      assertEquals(IdentificationType.DNI, user.identificationType)
      assertEquals(e.message, UserMessages.INVALID_IDENTIFICATION_TYPE)
    }
  }

  /**
   * Checks the assignment to null
   */
  @Test
  fun nullIdentificationType() {
    user.setIdentificationType(null)
    assertNull(user.identificationType)
  }
}