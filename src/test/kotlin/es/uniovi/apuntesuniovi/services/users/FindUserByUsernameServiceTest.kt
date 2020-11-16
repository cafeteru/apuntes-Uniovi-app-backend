package es.uniovi.apuntesuniovi.services.users

import es.uniovi.apuntesuniovi.entities.User
import es.uniovi.apuntesuniovi.infrastructure.constants.ExceptionMessages
import es.uniovi.apuntesuniovi.mocks.MockFactory
import es.uniovi.apuntesuniovi.repositories.UserRepository
import es.uniovi.apuntesuniovi.servicies.dtos.entities.UserDto
import es.uniovi.apuntesuniovi.servicies.dtos.impl.UserDtoAssembler
import es.uniovi.apuntesuniovi.servicies.impl.users.FindUserByUsernameService
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

@ExtendWith(MockitoExtension::class)
class FindUserByUsernameServiceTest {
    private lateinit var user: User
    private lateinit var userDto: UserDto
    private lateinit var username: String

    @Mock
    private lateinit var userRepository: UserRepository
    private val userDtoAssembler = UserDtoAssembler()
    private lateinit var findUserByUsernameService: FindUserByUsernameService

    @BeforeEach
    fun initTest() {
        val mockFactory = MockFactory()
        userDto = mockFactory.getDtos().createUserDto()
        user = userDtoAssembler.dtoToEntity(userDto)
        username = userDto.username!!
    }

    @Test
    fun validData() {
        Mockito.`when`(userRepository.findByUsername(username)).thenReturn(Optional.of(user))
        findUserByUsernameService = FindUserByUsernameService(userRepository, userDtoAssembler, userDto.username)
        val result = findUserByUsernameService.execute();
        assertNotNull(result)
        assertEquals(result, userDto)
    }

    @Test
    fun invalidData() {
        try {
            findUserByUsernameService = FindUserByUsernameService(userRepository, userDtoAssembler, userDto.username + "1")
            findUserByUsernameService.execute();
        } catch (e: IllegalArgumentException) {
            Assertions.assertEquals(e.message, ExceptionMessages.NOT_FOUND_USERNAME)
        }
    }

    @Test
    fun nullData() {
        try {
            findUserByUsernameService = FindUserByUsernameService(userRepository, userDtoAssembler, null)
            findUserByUsernameService.execute();
            fail("The username can´t be null")
        } catch (e: IllegalArgumentException) {
            Assertions.assertEquals(e.message, ExceptionMessages.INVALID_USERNAME)
        }
    }

    @Test
    fun emptyData() {
        try {
            findUserByUsernameService = FindUserByUsernameService(userRepository, userDtoAssembler, "")
            findUserByUsernameService.execute();
            fail("The username can´t be null")
        } catch (e: IllegalArgumentException) {
            Assertions.assertEquals(e.message, ExceptionMessages.INVALID_USERNAME)
        }
    }
}