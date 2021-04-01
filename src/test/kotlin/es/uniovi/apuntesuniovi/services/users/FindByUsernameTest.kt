package es.uniovi.apuntesuniovi.services.users

import es.uniovi.apuntesuniovi.dtos.assemblers.UserAssembler
import es.uniovi.apuntesuniovi.mocks.entities.MockUserCreator
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
 * Check find by username method of the UserService class
 */
@ExtendWith(MockitoExtension::class)
class FindByUsernameTest {
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
        val username = "username"
        val user = MockUserCreator().create()
        user.username = username
        val userDto = userAssembler.entityToDto(user)
        Mockito.`when`(userRepository.findByUsername(username)).thenReturn(Optional.of(user))
        val result = userService.findByUsername(username)
        assertNotEquals(userDto, result)
        assertEquals(user.id, result.id)
        assertNull(result.img)
        assertNull(result.password)
    }
}