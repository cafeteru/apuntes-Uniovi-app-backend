package es.uniovi.apuntesuniovi.entities.users

import es.uniovi.apuntesuniovi.entities.User
import es.uniovi.apuntesuniovi.entities.types.RoleType
import es.uniovi.apuntesuniovi.infrastructure.constants.ExceptionMessages
import es.uniovi.apuntesuniovi.mocks.MockFactory
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class NumberIdentificationTest {
    private lateinit var user: User

    @BeforeEach
    fun initData() {
        user = MockFactory().getEntities().createUser()
    }

    @Test
    fun validNumberIdentification() {
        val numberIdentification = "65057750L"
        user.numberIdentification = numberIdentification
        assertEquals(numberIdentification, user.numberIdentification)
    }

    @Test
    fun invalidNumberIdentification() {
        val numberIdentification = "T8057750L"
        try {
            user.numberIdentification = numberIdentification
            fail("NumberIdentification isn´t valid")
        } catch (e: java.lang.IllegalArgumentException) {
            assertNotEquals(numberIdentification, user.numberIdentification)
            assertEquals(e.message, ExceptionMessages.INVALID_IDENTIFICATION_NUMBER)
        }
    }

    @Test
    fun nullNumberIdentification() {
        user.numberIdentification = null
        assertEquals(null, user.numberIdentification)
    }

    @Test
    fun validRole() {
        user.setRole(RoleType.TEACHER.toString())
        assertEquals(RoleType.TEACHER, user.role)
    }

    @Test
    fun invalidRole() {
        try {
            user.setRole("No exists")
            fail("RoleType is invalid")
        } catch (e: IllegalArgumentException) {
            assertEquals(RoleType.STUDENT, user.role)
            assertEquals(e.message, ExceptionMessages.INVALID_ROLE_TYPE)
        }
    }

    @Test
    fun nullRole() {
        try {
            user.setRole(null)
            fail("RoleType can´t be null")
        } catch (e: IllegalArgumentException) {
            assertEquals(RoleType.STUDENT, user.role)
            assertEquals(e.message, ExceptionMessages.NULL_ROLE_TYPE)
        }
    }

    @Test
    fun emptyRole() {
        try {
            user.setRole("")
            fail("RoleType can´t be empty")
        } catch (e: IllegalArgumentException) {
            assertEquals(RoleType.STUDENT, user.role)
            assertEquals(e.message, ExceptionMessages.INVALID_ROLE_TYPE)
        }
    }

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