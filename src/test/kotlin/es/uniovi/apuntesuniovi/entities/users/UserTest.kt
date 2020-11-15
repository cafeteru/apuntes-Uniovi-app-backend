package es.uniovi.apuntesuniovi.entities.users

import es.uniovi.apuntesuniovi.entities.User
import es.uniovi.apuntesuniovi.entities.types.IdentificationType
import es.uniovi.apuntesuniovi.entities.types.RoleType
import es.uniovi.apuntesuniovi.infrastructure.constants.ExceptionMessages
import es.uniovi.apuntesuniovi.mocks.MockFactory
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.fail
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDate
import kotlin.test.assertNotEquals

class UserTest {
    private lateinit var user: User

    @BeforeEach
    fun initData() {
        user = MockFactory().getEntities().createUser()
    }

    @Test
    fun validIdentificationType() {
        user.setIdentificationType("DNI")
        assertEquals(IdentificationType.DNI, user.identificationType)
    }

    @Test
    fun invalidIdentificationType() {
        try {
            user.setIdentificationType("No exists")
            fail("IdentificationType is invalid")
        } catch (e: IllegalArgumentException) {
            assertEquals(IdentificationType.NIE, user.identificationType)
            assertEquals(e.message, ExceptionMessages.INVALID_IDENTIFICATION_TYPE)
        }
    }

    @Test
    fun nullIdentificationType() {
        try {
            user.setIdentificationType(null)
            fail("IdentificationType can´t be null")
        } catch (e: IllegalArgumentException) {
            assertEquals(IdentificationType.NIE, user.identificationType)
            assertEquals(e.message, ExceptionMessages.NULL_IDENTIFICATION_TYPE)
        }
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
    fun equalsTest() {
        val test = User()
        assertNotEquals(test, user)
        test.id = user.id
        assertNotEquals(test, user)
        test.username = user.username
        assertNotEquals(test, user)
        test.numberIdentification = user.numberIdentification
        assertEquals(test, user)
        test.name = user.name
        assertEquals(test, user)
    }
}