package es.uniovi.apuntesuniovi.services.users

import es.uniovi.apuntesuniovi.entities.User
import es.uniovi.apuntesuniovi.infrastructure.exceptions.messages.ExceptionMessagesUser
import es.uniovi.apuntesuniovi.mocks.MockFactory
import es.uniovi.apuntesuniovi.repositories.UserRepository
import es.uniovi.apuntesuniovi.servicies.dtos.entities.UserDto
import es.uniovi.apuntesuniovi.servicies.dtos.impl.UserDtoAssembler
import es.uniovi.apuntesuniovi.servicies.impl.users.FindUserByUsernameService
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
class FindUserByUsernameServiceTest {
    private lateinit var user: User
    private lateinit var userDto: UserDto
    private lateinit var username: String

    @Mock
    private lateinit var userRepository: UserRepository
    private val userDtoAssembler = UserDtoAssembler()

    /**
     * Create init data for the test
     */
    @BeforeEach
    fun initTest() {
        val mockFactory = MockFactory()
        userDto = mockFactory.getDtos().createUserDto()
        user = userDtoAssembler.dtoToEntity(userDto)
        username = userDto.username!!
    }

    /**
     * Checks the functionality with valid data
     */
    @Test
    fun validData() {
        Mockito.`when`(userRepository.findByUsername(username)).thenReturn(Optional.of(user))
        val findUserByUsernameService = FindUserByUsernameService(userRepository, userDtoAssembler, userDto.username)
        val result = findUserByUsernameService.execute()
        assertNotNull(result)
        assertEquals(result, userDto)
    }

    /**
     * Checks the functionality with invalid data
     */
    @Test
    fun invalidData() {
        try {
            val findUserByUsernameService = FindUserByUsernameService(userRepository, userDtoAssembler, userDto.username + "1")
            findUserByUsernameService.execute()
        } catch (e: IllegalArgumentException) {
            assertEquals(e.message, ExceptionMessagesUser.NOT_FOUND_USERNAME)
        }
    }

    /**
     * Checks the functionality with null data
     */
    @Test
    fun nullData() {
        try {
            val findUserByUsernameService = FindUserByUsernameService(userRepository, userDtoAssembler, null)
            findUserByUsernameService.execute()
            fail("The username can´t be null")
        } catch (e: IllegalArgumentException) {
            assertEquals(e.message, ExceptionMessagesUser.INVALID_USERNAME)
        }
    }

    /**
     * Checks the functionality with empty data
     */
    @Test
    fun emptyData() {
        try {
            val findUserByUsernameService = FindUserByUsernameService(userRepository, userDtoAssembler, "")
            findUserByUsernameService.execute()
            fail("The username can´t be null")
        } catch (e: IllegalArgumentException) {
            assertEquals(e.message, ExceptionMessagesUser.INVALID_USERNAME)
        }
    }
}