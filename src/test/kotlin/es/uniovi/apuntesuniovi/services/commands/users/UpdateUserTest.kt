package es.uniovi.apuntesuniovi.services.commands.users

import es.uniovi.apuntesuniovi.mocks.entities.MockUserCreator
import es.uniovi.apuntesuniovi.models.User
import es.uniovi.apuntesuniovi.repositories.AddressRepository
import es.uniovi.apuntesuniovi.repositories.UserRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.util.*
import kotlin.test.assertNull

/**
 * Check class SaveUserService
 */
@ExtendWith(MockitoExtension::class)
class UpdateUserTest {
  private lateinit var user: User
  private val encoder = BCryptPasswordEncoder()

  @Mock
  private lateinit var userRepository: UserRepository

  @Mock
  private lateinit var addressRepository: AddressRepository

  /**
   * Create init data for the test
   */
  @BeforeEach
  fun initTest() {
    user = MockUserCreator().create()
    user.password = encoder.encode(user.password)
  }

  /**
   * Checks the functionality with valid user
   */
  @Test
  fun validUser() {
    val id = user.id!!
    user.password = "newPassword"
    Mockito.`when`(userRepository.findById(id)).thenReturn(Optional.of(user))
    Mockito.`when`(userRepository.save(Mockito.any(User::class.java))).thenReturn(user)
    val result = UpdateUser(userRepository, addressRepository, id, user).execute()
    assertNotNull(result)
    assertEquals(result.password, user.password)
    assertEquals(result.address, user.address)
  }

  @Test
  fun validUserWithoutPassword() {
    val id = user.id!!
    user.password = null
    Mockito.`when`(userRepository.findById(id)).thenReturn(Optional.of(MockUserCreator().create()))
    Mockito.`when`(userRepository.save(Mockito.any(User::class.java))).thenReturn(user)
    val result = UpdateUser(userRepository, addressRepository, id, user).execute()
    assertNotNull(result)
    assertEquals(result.password, user.password)
    assertEquals(result.address, user.address)
  }

  @Test
  fun validUserWithoutAddress() {
    val id = user.id!!
    user.address = null
    Mockito.`when`(userRepository.findById(id)).thenReturn(Optional.of(MockUserCreator().create()))
    Mockito.`when`(userRepository.save(Mockito.any(User::class.java))).thenReturn(user)
    val result = UpdateUser(userRepository, addressRepository, id, user).execute()
    assertNotNull(result)
    assertEquals(result.password, user.password)
    assertEquals(result.address, user.address)
    assertNull(result.address)
  }
}