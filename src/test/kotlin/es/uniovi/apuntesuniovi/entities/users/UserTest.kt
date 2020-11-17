package es.uniovi.apuntesuniovi.entities.users

import es.uniovi.apuntesuniovi.entities.User
import es.uniovi.apuntesuniovi.entities.types.IdentificationType
import es.uniovi.apuntesuniovi.entities.types.RoleType
import es.uniovi.apuntesuniovi.infrastructure.constants.DatabaseLimits
import es.uniovi.apuntesuniovi.infrastructure.constants.ExceptionMessages
import es.uniovi.apuntesuniovi.mocks.MockFactory
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class UserTest {
    private lateinit var user: User

    @BeforeEach
    fun initData() {
        user = MockFactory().getEntities().createUser()
    }

    @Test
    fun limitName() {
        var name = ""
        for (i in 0 until DatabaseLimits.USER_NAME) {
            name += "1"
        }
        user.name = name
        assertEquals(name, user.name)
    }

    @Test
    fun upLimitName() {
        try {
            var name = ""
            for (i in 0..DatabaseLimits.USER_NAME) {
                name += "1"
            }
            user.name = name
            fail()
        } catch (e: IllegalArgumentException) {
            assertEquals(e.message, ExceptionMessages.LIMIT_USER_NAME)
        }
    }

    @Test
    fun nullName() {
        user.name = null
        assertEquals(null, user.name)
    }

    @Test
    fun limitSurname() {
        var surname = ""
        for (i in 0 until DatabaseLimits.USER_SURNAME) {
            surname += "1"
        }
        user.surname = surname
        assertEquals(surname, user.surname)
    }

    @Test
    fun upLimitSurname() {
        try {
            var surname = ""
            for (i in 0..DatabaseLimits.USER_SURNAME) {
                surname += "1"
            }
            user.surname = surname
            fail()
        } catch (e: IllegalArgumentException) {
            assertEquals(e.message, ExceptionMessages.LIMIT_USER_SURNAME)
        }
    }

    @Test
    fun nullSurname() {
        user.surname = null
        assertEquals(null, user.surname)
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
    fun emptyIdentificationType() {
        try {
            user.setIdentificationType("")
            fail("IdentificationType can´t be empty")
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
    fun equalsTest() {
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