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


@ExtendWith(MockitoExtension::class)
class SaveUserServiceTest {
    private lateinit var user: User
    private lateinit var userDto: UserDto

    @Mock
    private lateinit var userRepository: UserRepository
    private val userDtoAssembler = UserDtoAssembler()

    private fun bCryptPasswordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @BeforeEach
    fun initTest() {
        val mockFactory = MockFactory()
        userDto = mockFactory.getDtos().createUserDto()
        user = userDtoAssembler.dtoToEntity(userDto)
        user.password = bCryptPasswordEncoder().encode(user.password)
    }

    @Test
    fun validData() {
        Mockito.`when`(userRepository.save(Mockito.any(User::class.java))).thenReturn(user)
        val saveUserService = SaveUserService(userRepository, userDtoAssembler, userDto)
        val result = saveUserService.execute();
        assertNotNull(result)
        assertEquals(result.size, 1)
        assertNotEquals(result[0].password, userDto.password)
    }

    @Test
    fun nullData() {
        try {
            val saveUserService = SaveUserService(userRepository, userDtoAssembler, null)
            saveUserService.execute()
            fail()
        } catch (e: IllegalArgumentException) {
            assertEquals(e.message, ExceptionMessages.NULL_USER)
        }
    }
}