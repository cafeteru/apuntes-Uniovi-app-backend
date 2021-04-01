package es.uniovi.apuntesuniovi.models.users

import es.uniovi.apuntesuniovi.mocks.entities.MockUserCreator
import es.uniovi.apuntesuniovi.models.User
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/**
 * Class to test equals method
 */
class EqualsTest {
    private lateinit var user: User

    /**
     * Create init data for the test
     */
    @BeforeEach
    fun initData() {
        user = MockUserCreator().create()
    }

    /**
     * Check method equals
     */
    @Test
    fun equalsUser() {
        val user = User()
        assertNotEquals(user, this.user)
        user.id = this.user.id
        assertNotEquals(user, this.user)
        user.username = this.user.username
        assertNotEquals(user, this.user)
        user.numberIdentification = this.user.numberIdentification
        assertEquals(user, this.user)
        user.name = this.user.name
        assertEquals(user, this.user)
    }
}