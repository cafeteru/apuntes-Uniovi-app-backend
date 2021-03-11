package es.uniovi.apuntesuniovi.services.users

import es.uniovi.apuntesuniovi.mocks.entities.MockUserCreator
import es.uniovi.apuntesuniovi.repositories.AddressRepository
import es.uniovi.apuntesuniovi.repositories.UserRepository
import es.uniovi.apuntesuniovi.services.UserService
import es.uniovi.apuntesuniovi.services.dtos.assemblers.UserAssembler
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
  private val userAssembler = UserAssembler()

  /**
   * Create init data for the test
   */
  @BeforeEach
  fun initTest() {
    userService = UserService(userRepository, addressRepository, userAssembler)
  }

  /**
   * Checks the functionality with valid data
   */
  @Test
  fun validData() {
    val user = MockUserCreator().create()
    val userDto = userAssembler.entityToDto(user)
    Mockito.`when`(userRepository.findById(user.id!!)).thenReturn(Optional.of(user))
    Mockito.`when`(userRepository.save(user)).thenReturn(user)
    val result = userService.disable(userDto.id!!, !userDto.active)
    assertNotEquals(userDto, result)
    assertEquals(user.id, result.id)
  }
}