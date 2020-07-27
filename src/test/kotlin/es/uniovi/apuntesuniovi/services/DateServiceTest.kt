package es.uniovi.apuntesuniovi.services

import es.uniovi.apuntesuniovi.infrastructure.constants.ExceptionMessages
import es.uniovi.apuntesuniovi.servicies.dates.DateService
import org.joda.time.LocalDate
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class DateServiceTest {

    @Test
    fun dateToStringValid() {
        val date = LocalDate(1990, 12, 22)
        assertNotNull(date)
        assertEquals(DateService.dateToString(date), "22-12-1990")
    }

    @Test
    fun dateToStringNull() {
        try {
            assertEquals(DateService.dateToString(null), "22-12-1990")
            fail<Boolean>()
        } catch (e: Exception) {
            assertEquals(e.javaClass, IllegalArgumentException::class.java)
            assertEquals(e.message, ExceptionMessages.nullDate)
        }
    }
}