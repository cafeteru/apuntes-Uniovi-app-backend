package es.uniovi.apuntesuniovi.services.users

import es.uniovi.apuntesuniovi.dtos.assemblers.UserAssembler
import es.uniovi.apuntesuniovi.infrastructure.messages.UserMessages
import es.uniovi.apuntesuniovi.mocks.entities.MockUserCreator
import es.uniovi.apuntesuniovi.repositories.AddressRepository
import es.uniovi.apuntesuniovi.repositories.UserRepository
import es.uniovi.apuntesuniovi.services.UserService
import org.junit.jupiter.api.Assertions.*
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
class DisableUserTest {
    private lateinit var userService: UserService
    private lateinit var userAssembler: UserAssembler

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
        userAssembler = UserAssembler()
    }

    /**
     * Checks with valid id and existing user
     */
    @Test
    fun validIdAndExistUser() {
        val user = MockUserCreator().create()
        val userDto = userAssembler.entityToDto(user)
        Mockito.`when`(userRepository.findById(user.id!!)).thenReturn(Optional.of(user))
        Mockito.`when`(userRepository.save(user)).thenReturn(user)
        val result = userService.disable(userDto.id!!, !userDto.active!!)
        assertNotEquals(userDto, result)
        assertEquals(user.id, result.id)
    }

    /**
     * Checks with valid id and not existing user
     */
    @Test
    fun validIdAndNotExistUser() {
        try {
            userService.disable(1L, true)
            fail(UserMessages.NOT_FOUND)
        } catch (e: IllegalArgumentException) {
            assertEquals(e.message, UserMessages.NOT_FOUND)
        }
    }

    /**
     * Checks with invalid id
     */
    @Test
    fun invalidId() {
        try {
            userService.disable(-1L, true)
            fail(UserMessages.INVALID_ID)
        } catch (e: IllegalArgumentException) {
            assertEquals(e.message, UserMessages.INVALID_ID)
        }
    }
}