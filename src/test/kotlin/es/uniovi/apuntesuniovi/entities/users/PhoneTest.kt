package es.uniovi.apuntesuniovi.entities.users

import es.uniovi.apuntesuniovi.entities.User
import es.uniovi.apuntesuniovi.infrastructure.constants.ExceptionMessages
import es.uniovi.apuntesuniovi.mocks.MockFactory
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PhoneTest {
    private lateinit var user: User

    @BeforeEach
    fun initData() {
        user = MockFactory().getEntities().createUser()
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
}