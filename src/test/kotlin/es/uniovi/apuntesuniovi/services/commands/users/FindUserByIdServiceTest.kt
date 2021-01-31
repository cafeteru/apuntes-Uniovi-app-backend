package es.uniovi.apuntesuniovi.services.commands.users

import es.uniovi.apuntesuniovi.infrastructure.messages.UserMessages
import es.uniovi.apuntesuniovi.mocks.entities.MockUserCreator
import es.uniovi.apuntesuniovi.models.Address
import es.uniovi.apuntesuniovi.models.User
import es.uniovi.apuntesuniovi.repositories.UserRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.fail

/**
 * Check class FindUserByIdService
 */
@ExtendWith(MockitoExtension::class)
class FindUserByIdServiceTest {
  private lateinit var user: User
  private var address: Address? = null

  @Mock
  private lateinit var userRepository: UserRepository

  /**
   * Create init data for the test
   */
  @BeforeEach
  fun initTest() {
    user = MockUserCreator().create()
    address = user.address
  }

  /**
   * Checks with valid id and existing user
   */
  @Test
  fun validIdAndExistUser() {
    val id = 1L
    Mockito.`when`(userRepository.findById(id)).thenReturn(Optional.of(user))
    val findUserByIdService = FindUserByIdService(userRepository, id)
    val result = findUserByIdService.execute()
    assertNotNull(result)
    assertEquals(user, result)
    assertEquals(user.address, result.address)
  }

  /**
   * Checks with valid id and not existing user
   */
  @Test
  fun validIdAndNotExistUser() {
    val id = 1L
    val findUserByIdService = FindUserByIdService(userRepository, id)
    try {
      findUserByIdService.execute()
      fail("User not found")
    } catch (e: IllegalArgumentException) {
      Assertions.assertEquals(e.message, UserMessages.NOT_FOUND)
    }
  }

  /**
   * Checks with invalid id
   */
  @Test
  fun invalidId() {
    val id = -1L
    val findUserByIdService = FindUserByIdService(userRepository, id)
    try {
      findUserByIdService.execute()
      fail("Invalid user id")
    } catch (e: IllegalArgumentException) {
      Assertions.assertEquals(e.message, UserMessages.INVALID_ID)
    }
  }
}