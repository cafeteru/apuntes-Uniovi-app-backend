package es.uniovi.apuntesuniovi.services.commands.users

import es.uniovi.apuntesuniovi.infrastructure.messages.UserMessages
import es.uniovi.apuntesuniovi.mocks.entities.MockUserCreator
import es.uniovi.apuntesuniovi.models.User
import es.uniovi.apuntesuniovi.repositories.UserRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*

/**
 * Check class FindUserByUsernameService
 */
@ExtendWith(MockitoExtension::class)
class FindUserByUsernameTest {
  private lateinit var user: User
  private lateinit var username: String

  @Mock
  private lateinit var userRepository: UserRepository

  /**
   * Create init data for the test
   */
  @BeforeEach
  fun initTest() {
    user = MockUserCreator().create()
    username = user.username!!
  }

  /**
   * Checks the functionality with valid data
   */
  @Test
  fun validData() {
    Mockito.`when`(userRepository.findByUsername(username)).thenReturn(Optional.of(user))
    val findUserByUsernameService = FindUserByUsername(userRepository, user.username)
    val result = findUserByUsernameService.execute()
    assertNotNull(result)
    assertEquals(result, user)
  }

  /**
   * Checks the functionality with invalid data
   */
  @Test
  fun invalidData() {
    try {
      val findUserByUsernameService =
        FindUserByUsername(userRepository, user.username + "1")
      findUserByUsernameService.execute()
    } catch (e: IllegalArgumentException) {
      assertEquals(e.message, UserMessages.NOT_FOUND_USERNAME)
    }
  }

  /**
   * Checks the functionality with null data
   */
  @Test
  fun nullData() {
    try {
      val findUserByUsernameService = FindUserByUsername(userRepository, null)
      findUserByUsernameService.execute()
      fail("The username can´t be null")
    } catch (e: IllegalArgumentException) {
      assertEquals(e.message, UserMessages.INVALID_USERNAME)
    }
  }

  /**
   * Checks the functionality with empty data
   */
  @Test
  fun emptyData() {
    try {
      val findUserByUsernameService = FindUserByUsername(userRepository, "")
      findUserByUsernameService.execute()
      fail("The username can´t be null")
    } catch (e: IllegalArgumentException) {
      assertEquals(e.message, UserMessages.INVALID_USERNAME)
    }
  }
}