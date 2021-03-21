package es.uniovi.apuntesuniovi.services.users

import es.uniovi.apuntesuniovi.mocks.entities.MockUserCreator
import es.uniovi.apuntesuniovi.models.User
import es.uniovi.apuntesuniovi.repositories.AddressRepository
import es.uniovi.apuntesuniovi.repositories.UserRepository
import es.uniovi.apuntesuniovi.services.UserService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertNull

/**
 * Check the update method of the UserService class
 */
@ExtendWith(MockitoExtension::class)
class UpdateTest {
  @Mock
  private lateinit var userRepository: UserRepository

  @Mock
  private lateinit var addressRepository: AddressRepository

  private lateinit var userService: UserService

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
    Mockito.`when`(userRepository.findByUsername(user.username!!)).thenReturn(Optional.of(user))
    Mockito.`when`(userRepository.findByNumberIdentification(user.numberIdentification!!))
      .thenReturn(Optional.of(user))
    Mockito.`when`(userRepository.save(Mockito.any(User::class.java))).thenReturn(user)
    val result = userService.update(user.id!!, user)
    assertEquals(user, result)
  }
}