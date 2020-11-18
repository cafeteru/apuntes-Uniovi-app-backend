package es.uniovi.apuntesuniovi.services.users

import es.uniovi.apuntesuniovi.entities.User
import es.uniovi.apuntesuniovi.infrastructure.constants.ExceptionMessages
import es.uniovi.apuntesuniovi.mocks.MockFactory
import es.uniovi.apuntesuniovi.repositories.UserRepository
import es.uniovi.apuntesuniovi.servicies.dtos.entities.UserDto
import es.uniovi.apuntesuniovi.servicies.dtos.impl.UserDtoAssembler
import es.uniovi.apuntesuniovi.servicies.impl.users.SaveUserService
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.util.*


@ExtendWith(MockitoExtension::class)
class SaveUserServiceTest {
    private lateinit var user: User
    private lateinit var userDto: UserDto
    private val encoder = BCryptPasswordEncoder()

    @Mock
    private lateinit var userRepository: UserRepository
    private val userDtoAssembler = UserDtoAssembler()

    @BeforeEach
    fun initTest() {
        val mockFactory = MockFactory()
        userDto = mockFactory.getDtos().createUserDto()
        user = userDtoAssembler.dtoToEntity(userDto)
        user.password = encoder.encode(user.password)
    }

    @Test
    fun validUser() {
        Mockito.`when`(userRepository.save(Mockito.any(User::class.java))).thenReturn(user)
        val saveUserService = SaveUserService(userRepository, userDtoAssembler, userDto)
        val result = saveUserService.execute()
        assertNotNull(result)
        assertEquals(result.size, 1)
        assertNotEquals(result[0].password, userDto.password)
    }

    @Test
    fun nullUser() {
        try {
            val saveUserService = SaveUserService(userRepository, userDtoAssembler, null)
            saveUserService.execute()
            fail()
        } catch (e: IllegalArgumentException) {
            assertEquals(e.message, ExceptionMessages.INVALID_DATA_USER)
        }
    }

    @Test
    fun existedUser() {
        Mockito.`when`(userRepository.findByUsername(userDto.username!!)).thenReturn(Optional.of(user))
        try {
            val saveUserService = SaveUserService(userRepository, userDtoAssembler, userDto)
            saveUserService.execute()
            fail("User already registered")
        } catch (e: IllegalArgumentException) {
            assertEquals(e.message, ExceptionMessages.ALREADY_REGISTERED_USERNAME)
        }
    }

    @Test
    fun nullUsername() {
        try {
            userDto.username = null
            val saveUserService = SaveUserService(userRepository, userDtoAssembler, userDto)
            saveUserService.execute()
            fail("Username can´t be null")
        } catch (e: IllegalArgumentException) {
            assertEquals(e.message, ExceptionMessages.INVALID_DATA_USER)
        }
    }

    @Test
    fun emptyUsername() {
        try {
            userDto.username = ""
            val saveUserService = SaveUserService(userRepository, userDtoAssembler, userDto)
            saveUserService.execute()
            fail("Username can´t be empty")
        } catch (e: IllegalArgumentException) {
            assertEquals(e.message, ExceptionMessages.INVALID_DATA_USER)
        }
    }

    @Test
    fun nullPassword() {
        try {
            userDto.password = null
            val saveUserService = SaveUserService(userRepository, userDtoAssembler, userDto)
            saveUserService.execute()
            fail("Password can´t be null")
        } catch (e: IllegalArgumentException) {
            assertEquals(e.message, ExceptionMessages.INVALID_DATA_USER)
        }
    }

    @Test
    fun emptyPassword() {
        try {
            userDto.password = ""
            val saveUserService = SaveUserService(userRepository, userDtoAssembler, userDto)
            saveUserService.execute()
            fail("Password can´t be empty")
        } catch (e: IllegalArgumentException) {
            assertEquals(e.message, ExceptionMessages.INVALID_DATA_USER)
        }
    }
}