package es.uniovi.apuntesuniovi.models.users

import es.uniovi.apuntesuniovi.infrastructure.messages.UserMessages
import es.uniovi.apuntesuniovi.mocks.entities.MockUser
import es.uniovi.apuntesuniovi.models.User
import es.uniovi.apuntesuniovi.models.types.RoleType
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.fail
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertNull

/**
 * Test the assignments to role of a user
 */
class RoleTest {
    private lateinit var user: User

    /**
     * Create init data for the test
     */
    @BeforeEach
    fun initData() {
        user = MockUser().create()
    }

    /**
     * Checks the assignment with valid data
     */
    @Test
    fun validRole() {
        user.setRole(RoleType.ROLE_TEACHER.toString())
        assertEquals(RoleType.ROLE_TEACHER, user.role)
    }

    /**
     * Checks the assignment with invalid data
     */
    @Test
    fun invalidRole() {
        val role = user.role
        try {
            user.setRole("No exists")
            fail("RoleType is invalid")
        } catch (e: IllegalArgumentException) {
            assertEquals(role, user.role)
            assertEquals(e.message, UserMessages.INVALID_ROLE_TYPE)
        }
    }

    /**
     * Checks the assignment to null
     */
    @Test
    fun nullRole() {
        user.setRole(null)
        assertNull(user.role)
    }

    /**
     * Checks the assignment to empty
     */
    @Test
    fun emptyRole() {
        val role = user.role
        try {
            user.setRole("")
            fail("RoleType can´t be empty")
        } catch (e: IllegalArgumentException) {
            assertEquals(role, user.role)
            assertEquals(e.message, UserMessages.INVALID_ROLE_TYPE)
        }
    }
}