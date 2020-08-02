package es.uniovi.apuntesuniovi.services.users

import es.uniovi.apuntesuniovi.entities.User
import es.uniovi.apuntesuniovi.entities.types.RoleType
import es.uniovi.apuntesuniovi.repositories.UserRepository
import es.uniovi.apuntesuniovi.servicies.dtos.entities.UserDto
import es.uniovi.apuntesuniovi.servicies.dtos.impl.UserDtoAssembler
import es.uniovi.apuntesuniovi.servicies.impl.users.SaveUserService
import org.junit.jupiter.api.Assertions.assertNotNull
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

    @Mock
    private lateinit var userRepository: UserRepository
    private lateinit var userDtoAssembler: UserDtoAssembler
    private lateinit var saveUserService: SaveUserService

    private fun bCryptPasswordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @BeforeEach
    fun initTest() {
        val userDto = UserDto(
                id = 3,
                name = "admin",
                surname = "admin",
                active = true,
                birthDate = "22-12-1990",
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
        saveUserService = SaveUserService(userRepository, userDtoAssembler,
                bCryptPasswordEncoder(), userDto)
        Mockito.`when`(userRepository.save(Mockito.any(User::class.java))).thenReturn(user)
    }

    @Test
    fun validData() {
        assertNotNull(saveUserService.execute())
    }
}