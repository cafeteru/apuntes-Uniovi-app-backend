package es.uniovi.apuntesuniovi.entities.users

import es.uniovi.apuntesuniovi.entities.User
import es.uniovi.apuntesuniovi.infrastructure.constants.ExceptionMessages
import es.uniovi.apuntesuniovi.mocks.MockFactory
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.fail
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDate

class BirthDateTest {
    private lateinit var user: User

    @BeforeEach
    fun initData() {
        user = MockFactory().getEntities().createUser()
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
}