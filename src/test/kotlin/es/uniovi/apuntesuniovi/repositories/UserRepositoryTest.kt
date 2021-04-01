package es.uniovi.apuntesuniovi.repositories

import es.uniovi.apuntesuniovi.mocks.entities.MockUserCreator
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import kotlin.test.assertEquals


/**
 * Check UserRepository
 */
@ExtendWith(SpringExtension::class)
@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private lateinit var userRepository: UserRepository

    /**
     * Checks the functionality with valid data
     */
    @Test
    fun findByUsernameTest() {
        val user = MockUserCreator().createWithoutId()
        user.address = null
        userRepository.save(user)
        val optional = userRepository.findByUsername(user.username!!)
        assertNotNull(optional.isPresent)
        val result = optional.get()
        assertNotNull(result.id)
        assertEquals(result.username, user.username)
    }
}