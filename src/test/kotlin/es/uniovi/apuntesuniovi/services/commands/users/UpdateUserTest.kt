package es.uniovi.apuntesuniovi.services.commands.users

import es.uniovi.apuntesuniovi.infrastructure.messages.UserMessages
import es.uniovi.apuntesuniovi.mocks.entities.MockUserCreator
import es.uniovi.apuntesuniovi.models.User
import es.uniovi.apuntesuniovi.repositories.AddressRepository
import es.uniovi.apuntesuniovi.repositories.UserRepository
import org.junit.jupiter.api.Assertions.*
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
    Mockito.`when`(userRepository.findByUsername(user.username!!)).thenReturn(Optional.of(user))
    Mockito.`when`(userRepository.findByNumberIdentification(user.numberIdentification!!)).thenReturn(Optional.of(user))
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
    Mockito.`when`(userRepository.findByUsername(user.username!!)).thenReturn(Optional.empty())
    Mockito.`when`(userRepository.findByNumberIdentification(user.numberIdentification!!))
      .thenReturn(Optional.of(MockUserCreator().create()))
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
    Mockito.`when`(userRepository.findByUsername(user.username!!)).thenReturn(Optional.of(user))
    Mockito.`when`(userRepository.findByNumberIdentification(user.numberIdentification!!))
      .thenReturn(Optional.of(MockUserCreator().create()))
    Mockito.`when`(userRepository.save(Mockito.any(User::class.java))).thenReturn(user)
    val result = UpdateUser(userRepository, addressRepository, id, user).execute()
    assertNotNull(result)
    assertEquals(result.password, user.password)
    assertEquals(result.address, user.address)
    assertNull(result.address)
  }

  @Test
  fun invalidId() {
    try {
      val id = 2L
      UpdateUser(userRepository, addressRepository, id, user).execute()
      fail()
    } catch (e: IllegalArgumentException) {
      assertEquals(e.message, UserMessages.NOT_FOUND)
    }
  }

  @Test
  fun nullUsername() {
    try {
      val id = user.id!!
      user.username = null
      Mockito.`when`(userRepository.findById(id)).thenReturn(Optional.of(MockUserCreator().create()))
      UpdateUser(userRepository, addressRepository, id, user).execute()
      fail()
    } catch (e: IllegalArgumentException) {
      assertEquals(e.message, UserMessages.INVALID_DATA_USER)
    }
  }

  @Test
  fun existedUsername() {
    try {
      val id = user.id!!
      val user2 = MockUserCreator().create()
      user2.id = id + 1
      Mockito.`when`(userRepository.findById(id)).thenReturn(Optional.of(user))
      Mockito.`when`(userRepository.findByUsername(user.username!!)).thenReturn(Optional.of(user2))
      UpdateUser(userRepository, addressRepository, id, user).execute()
      fail()
    } catch (e: IllegalArgumentException) {
      assertEquals(e.message, UserMessages.ALREADY_REGISTERED_USERNAME)
    }
  }

  @Test
  fun notExistedUsername() {
    val id = user.id!!
    Mockito.`when`(userRepository.findById(id)).thenReturn(Optional.of(user))
    Mockito.`when`(userRepository.findByUsername(user.username!!)).thenReturn(Optional.empty())
    Mockito.`when`(userRepository.findByNumberIdentification(user.numberIdentification!!))
      .thenReturn(Optional.empty())
    Mockito.`when`(userRepository.save(Mockito.any(User::class.java))).thenReturn(user)
    val result = UpdateUser(userRepository, addressRepository, id, user).execute()
    assertNotNull(result)
    assertEquals(result.password, user.password)
    assertEquals(result.address, user.address)
    assertNull(result.address)
  }

  @Test
  fun nullNumberIdentification() {
    try {
      val id = user.id!!
      user.numberIdentification = null
      Mockito.`when`(userRepository.findById(id)).thenReturn(Optional.of(MockUserCreator().create()))
      UpdateUser(userRepository, addressRepository, id, user).execute()
      fail()
    } catch (e: IllegalArgumentException) {
      assertEquals(e.message, UserMessages.INVALID_DATA_USER)
    }
  }

  @Test
  fun existedNumberIdentification() {
    try {
      val id = user.id!!
      val user2 = MockUserCreator().create()
      user2.id = id + 1
      Mockito.`when`(userRepository.findById(id)).thenReturn(Optional.of(user))
      Mockito.`when`(userRepository.findByUsername(user.username!!)).thenReturn(Optional.of(user))
      Mockito.`when`(userRepository.findByNumberIdentification(user.numberIdentification!!))
        .thenReturn(Optional.of(user2))
      UpdateUser(userRepository, addressRepository, id, user).execute()
      fail()
    } catch (e: IllegalArgumentException) {
      assertEquals(e.message, UserMessages.ALREADY_REGISTERED_NUMBER_IDENTIFICATION)
    }
  }

  @Test
  fun notExistedNumberIdentification() {
    val id = user.id!!
    Mockito.`when`(userRepository.findById(id)).thenReturn(Optional.of(user))
    Mockito.`when`(userRepository.findByUsername(user.username!!)).thenReturn(Optional.of(user))
    Mockito.`when`(userRepository.findByNumberIdentification(user.numberIdentification!!))
      .thenReturn(Optional.empty())
    Mockito.`when`(userRepository.save(Mockito.any(User::class.java))).thenReturn(user)
    val result = UpdateUser(userRepository, addressRepository, id, user).execute()
    assertNotNull(result)
    assertEquals(result.password, user.password)
    assertEquals(result.address, user.address)
    assertNull(result.address)
  }
}