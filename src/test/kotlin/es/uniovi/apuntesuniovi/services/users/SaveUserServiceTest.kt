package es.uniovi.apuntesuniovi.services.users

import es.uniovi.apuntesuniovi.entities.User
import es.uniovi.apuntesuniovi.entities.types.RoleType
import es.uniovi.apuntesuniovi.repositories.UserRepository
import es.uniovi.apuntesuniovi.servicies.dtos.entities.UserDto
import es.uniovi.apuntesuniovi.servicies.dtos.impl.UserDtoAssembler
import es.uniovi.apuntesuniovi.servicies.impl.users.SaveUserService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.time.LocalDate
import kotlin.test.assertEquals


@ExtendWith(MockitoExtension::class)
class SaveUserServiceTest {
    private lateinit var user: User
    private lateinit var userDto: UserDto

    @Mock
    private lateinit var userRepository: UserRepository
    private lateinit var userDtoAssembler: UserDtoAssembler
    private lateinit var saveUserService: SaveUserService

    private fun bCryptPasswordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @BeforeEach
    fun initTest() {
        userDto = UserDto(
                id = 3,
                name = "admin",
                surname = "admin",
                active = true,
                birthDate = LocalDate.of(1990, 12, 22),
                email = "admin@admin.com",
                identificationType = "dni",
                img = "",
                numberIdentification = "",
                password = "admin",
                phone = "",
                username = "admin",
                role = RoleType.ADMIN.toString()
        )
        userDtoAssembler = UserDtoAssembler()
        user = userDtoAssembler.dtoToEntity(userDto)
        user.password = bCryptPasswordEncoder().encode(user.password)
        saveUserService = SaveUserService(userRepository, userDtoAssembler, userDto)
        Mockito.`when`(userRepository.save(Mockito.any(User::class.java))).thenReturn(user)
    }

    @Test
    fun validData() {
        val result = saveUserService.execute();
        assertNotNull(result)
        assertEquals(result.size, 1)
        Assertions.assertNotEquals(result[0].password, userDto.password)
    }
}