package es.uniovi.apuntesuniovi.services.users

import es.uniovi.apuntesuniovi.entities.User
import es.uniovi.apuntesuniovi.servicies.dtos.entities.UserDto
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class FindUserByUsernameServiceTest {
    private lateinit var user: User
    private lateinit var userDto: UserDto
}