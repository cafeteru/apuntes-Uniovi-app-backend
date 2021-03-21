package es.uniovi.apuntesuniovi.services.users

import es.uniovi.apuntesuniovi.mocks.entities.MockUserCreator
import es.uniovi.apuntesuniovi.repositories.AddressRepository
import es.uniovi.apuntesuniovi.repositories.UserRepository
import es.uniovi.apuntesuniovi.services.UserService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*

/**
 * Check disable method of the UserService class
 */
@ExtendWith(MockitoExtension::class)
class DisableTest {
  private lateinit var userService: UserService

  @Mock
  private lateinit var userRepository: UserRepository

  @Mock
  private lateinit var addressRepository: AddressRepository

  /**
   * Create init data for the test
   */
  @BeforeEach
  fun initTest() {
    userService = UserService(userRepository, addressRepository)
  }

  /**
   * Checks the functionality with valid data
   */
  @Test
  fun validData() {
    val user = MockUserCreator().create()
    Mockito.`when`(userRepository.findById(user.id!!)).thenReturn(Optional.of(user))
    val user2 = MockUserCreator().create()
    user2.active = !user2.active
    Mockito.`when`(userRepository.save(user)).thenReturn(user2)
    val result = userService.disable(user.id!!, !user.active)
    assertNotEquals(user, result)
    assertEquals(user.id, result.id)
  }
}