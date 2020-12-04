package es.uniovi.apuntesuniovi.entities.users

import es.uniovi.apuntesuniovi.entities.User
import es.uniovi.apuntesuniovi.entities.types.RoleType
import es.uniovi.apuntesuniovi.infrastructure.exceptions.messages.UserMessages
import es.uniovi.apuntesuniovi.mocks.entities.MockUserCreator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.fail
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

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
        user = MockUserCreator().create()
    }

    /**
     * Checks the assignment with valid data
     */
    @Test
    fun validRole() {
        user.setRole(RoleType.TEACHER.toString())
        assertEquals(RoleType.TEACHER, user.role)
    }

    /**
     * Checks the assignment with invalid data
     */
    @Test
    fun invalidRole() {
        try {
            user.setRole("No exists")
            fail("RoleType is invalid")
        } catch (e: IllegalArgumentException) {
            assertEquals(RoleType.STUDENT, user.role)
            assertEquals(e.message, UserMessages.INVALID_ROLE_TYPE)
        }
    }

    /**
     * Checks the assignment to null
     */
    @Test
    fun nullRole() {
        try {
            user.setRole(null)
            fail("RoleType can´t be null")
        } catch (e: IllegalArgumentException) {
            assertEquals(RoleType.STUDENT, user.role)
            assertEquals(e.message, UserMessages.NULL_ROLE_TYPE)
        }
    }

    /**
     * Checks the assignment to empty
     */
    @Test
    fun emptyRole() {
        try {
            user.setRole("")
            fail("RoleType can´t be empty")
        } catch (e: IllegalArgumentException) {
            assertEquals(RoleType.STUDENT, user.role)
            assertEquals(e.message, UserMessages.INVALID_ROLE_TYPE)
        }
    }
}