package es.uniovi.apuntesuniovi.entities.users

import es.uniovi.apuntesuniovi.entities.Subject
import es.uniovi.apuntesuniovi.entities.User
import es.uniovi.apuntesuniovi.entities.types.IdentificationType
import es.uniovi.apuntesuniovi.entities.types.RoleType
import es.uniovi.apuntesuniovi.infrastructure.constants.DatabaseLimits
import es.uniovi.apuntesuniovi.infrastructure.constants.ExceptionMessages
import es.uniovi.apuntesuniovi.mocks.MockFactory
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDate

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
            fail("Name is too big")
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
            fail("Surname is too big")
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
    fun limitEmail() {
        var email = ""
        val aux = DatabaseLimits.USER_EMAIL / 2
        for (i in 1 until aux) {
            email += "1"
        }
        email += "@"
        for (i in 3 until DatabaseLimits.USER_EMAIL - aux) {
            email += "1"
        }
        email += ".es"
        user.email = email
        assertEquals(email, user.email)
    }

    @Test
    fun upLimitEmail() {
        try {
            var email = ""
            val aux = DatabaseLimits.USER_EMAIL / 2
            for (i in 0 until aux) {
                email += "1"
            }
            email += "@"
            for (i in 3 until DatabaseLimits.USER_EMAIL - aux) {
                email += "1"
            }
            email += ".es"
            user.email = email
            assertEquals(email, user.email)
            fail("Email is too big")
        } catch (e: IllegalArgumentException) {
            assertEquals(e.message, ExceptionMessages.INVALID_EMAIL)
        }
    }

    @Test
    fun nullEmail() {
        user.email = null
        assertEquals(null, user.email)
    }

    @Test
    fun validPhone() {
        val phone = "652789456"
        user.phone = phone
        assertEquals(phone, user.phone)
    }

    @Test
    fun invalidPhone() {
        val phone = "652789S456"
        try {
            user.phone = phone
            fail("Phone isn´t valid")
        } catch (e: IllegalArgumentException) {
            assertNotEquals(user.phone, phone)
            assertEquals(e.message, ExceptionMessages.INVALID_PHONE)
        }
    }

    @Test
    fun nullPhone() {
        user.phone = null
        assertEquals(null, user.phone)
    }

    @Test
    fun limitImg() {
        var img = ""
        for (i in 0 until DatabaseLimits.USER_IMG) {
            img += "1"
        }
        user.img = img
        assertEquals(img, user.img)
    }

    @Test
    fun upLimitImg() {
        try {
            var img = ""
            for (i in 0..DatabaseLimits.USER_IMG) {
                img += "1"
            }
            user.img = img
            fail("Img is too big")
        } catch (e: IllegalArgumentException) {
            assertEquals(e.message, ExceptionMessages.LIMIT_USER_IMG)
        }
    }

    @Test
    fun nullImg() {
        user.img = null
        assertEquals(null, user.img)
    }

    @Test
    fun limitBirthDate() {
        val birthDate = LocalDate.now()
        user.birthDate = birthDate
        assertEquals(birthDate, user.birthDate)
    }

    @Test
    fun upLimitBirthDate() {
        try {
            val birthDate = LocalDate.now().plusDays(1)
            user.birthDate = birthDate
            fail("BirthDate is too big")
        } catch (e: IllegalArgumentException) {
            assertEquals(e.message, ExceptionMessages.LIMIT_USER_BIRTH_DATE)
        }
    }

    @Test
    fun nullBirthDate() {
        user.birthDate = null
        assertEquals(null, user.birthDate)
    }

    @Test
    fun limitUsername() {
        var username = ""
        for (i in 0 until DatabaseLimits.USER_USERNAME) {
            username += "1"
        }
        user.username = username
        assertEquals(username, user.username)
    }

    @Test
    fun upLimitUsername() {
        try {
            var username = ""
            for (i in 0..DatabaseLimits.USER_NAME) {
                username += "1"
            }
            user.username = username
            fail("Username is too big")
        } catch (e: IllegalArgumentException) {
            assertEquals(e.message, ExceptionMessages.LIMIT_USER_USERNAME)
        }
    }

    @Test
    fun nullUsername() {
        user.username = null
        assertEquals(null, user.username)
    }

    @Test
    fun limitPassword() {
        var password = ""
        for (i in 0 until DatabaseLimits.USER_PASSWORD) {
            password += "1"
        }
        user.password = password
        assertEquals(password, user.password)
    }

    @Test
    fun upLimitPassword() {
        try {
            var password = ""
            for (i in 0..DatabaseLimits.USER_PASSWORD) {
                password += "1"
            }
            user.password = password
            fail("Password is too big")
        } catch (e: IllegalArgumentException) {
            assertEquals(e.message, ExceptionMessages.LIMIT_USER_PASSWORD)
        }
    }

    @Test
    fun nullPassword() {
        user.password = null
        assertEquals(null, user.password)
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