package es.uniovi.apuntesuniovi.services.users.update

import es.uniovi.apuntesuniovi.mocks.entities.MockUserCreator
import es.uniovi.apuntesuniovi.models.User
import es.uniovi.apuntesuniovi.repositories.AddressRepository
import es.uniovi.apuntesuniovi.repositories.UserRepository
import es.uniovi.apuntesuniovi.services.commands.users.UpdateUser
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.util.*
import kotlin.test.assertEquals

/**
 * Check class UpdateUser
 */
@ExtendWith(MockitoExtension::class)
class NullNumberIdentificationTest {
    private lateinit var user: User
    private val encoder = BCryptPasswordEncoder()

    @Mock
    private lateinit var userRepository: UserRepository

    @Mock
    private lateinit var addressRepository: AddressRepository

    /**
     * Create init data for the test
     */
    @BeforeEach
    fun initTest() {
        user = MockUserCreator().create()
        user.password = encoder.encode(user.password)
    }

    /**
     * Check with valid user and null number of identification
     */
    @Test
    fun nullNumberIdentification() {
        val id = user.id!!
        user.numberIdentification = null
        Mockito.`when`(userRepository.findById(id)).thenReturn(Optional.of(MockUserCreator().create()))
        Mockito.`when`(userRepository.save(user)).thenReturn(user)
        val result = UpdateUser(userRepository, addressRepository, id, user).execute()
        assertEquals(result, user)
    }
}