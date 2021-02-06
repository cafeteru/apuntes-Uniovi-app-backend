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
 * Test the assignments to username of a user
 */
class UsernameTest {
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
  fun limitUsername() {
    var username = ""
    for (i in 0 until UserLimits.USERNAME) {
      username += "1"
    }
    user.username = username
    assertEquals(username, user.username)
  }

  /**
   * Checks the assignment over the limit
   */
  @Test
  fun upLimitUsername() {
    try {
      var username = ""
      for (i in 0..UserLimits.NAME) {
        username += "1"
      }
      user.username = username
      fail("Username is too big")
    } catch (e: IllegalArgumentException) {
      assertEquals(e.message, UserMessages.LIMIT_USERNAME)
    }
  }


  /**
   * Checks the assignment to null
   */
  @Test
  fun nullUsername() {
    user.username = null
    assertEquals(null, user.username)
  }

  /**
   * Checks the assignment to empty
   */
  @Test
  fun emptyUsername() {
    try {
      user.username = ""
      fail("Username can´t be empty")
    } catch (e: IllegalArgumentException) {
      assertEquals(e.message, UserMessages.LIMIT_USERNAME)
    }
  }
}