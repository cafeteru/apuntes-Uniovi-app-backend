package es.uniovi.apuntesuniovi.models.users

import es.uniovi.apuntesuniovi.infrastructure.constants.database.UserLimits
import es.uniovi.apuntesuniovi.infrastructure.messages.UserMessages
import es.uniovi.apuntesuniovi.mocks.entities.MockUserCreator
import es.uniovi.apuntesuniovi.models.User
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.fail
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/**
 * Test the assignments to email of a user
 */
class EmailTest {
  private lateinit var user: User

  /**
   * Create init data for the test
   */
  @BeforeEach
  fun initData() {
    user = MockUserCreator().create()
  }

  /**
   * Checks the assignment under the limit
   */
  @Test
  fun limitEmail() {
    var email = ""
    val aux = UserLimits.EMAIL / 2
    for (i in 1 until aux) {
      email += "1"
    }
    email += "@"
    for (i in 3 until UserLimits.EMAIL - aux) {
      email += "1"
    }
    email += ".es"
    user.email = email
    assertEquals(email, user.email)
  }

  /**
   * Checks the assignment over the limit
   */
  @Test
  fun upLimitEmail() {
    try {
      var email = ""
      val aux = UserLimits.EMAIL / 2
      for (i in 0 until aux) {
        email += "1"
      }
      email += "@"
      for (i in 3 until UserLimits.EMAIL - aux) {
        email += "1"
      }
      email += ".es"
      user.email = email
      assertEquals(email, user.email)
      fail("Email is too big")
    } catch (e: IllegalArgumentException) {
      assertEquals(e.message, UserMessages.INVALID_EMAIL)
    }
  }

  /**
   * Checks the assignment to null
   */
  @Test
  fun nullEmail() {
    user.email = null
    assertEquals(null, user.email)
  }

  /**
   * Checks the assignment to empty
   */
  @Test
  fun emptyEmail() {
    try {
      user.email = ""
      fail("Email can´t be empty")
    } catch (e: IllegalArgumentException) {
      assertEquals(e.message, UserMessages.INVALID_EMAIL)
    }
  }
}