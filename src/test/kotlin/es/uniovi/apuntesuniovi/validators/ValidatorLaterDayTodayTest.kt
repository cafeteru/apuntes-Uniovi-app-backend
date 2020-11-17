package es.uniovi.apuntesuniovi.validators

import es.uniovi.apuntesuniovi.validators.impl.ValidatorLaterDayToday
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.time.LocalDate

class ValidatorLaterDayTodayTest {
    @Test
    fun validData() {
        var date = LocalDate.now()
        var validator = ValidatorLaterDayToday(date)
        Assertions.assertTrue(validator.isValid())
        date = LocalDate.now().minusDays(1)
        validator = ValidatorLaterDayToday(date)
        Assertions.assertTrue(validator.isValid())
    }

    @Test
    fun nullData() {
        val validator = ValidatorLaterDayToday(null)
        Assertions.assertTrue(validator.isValid())
    }

    @Test
    fun invalidData() {
        val date = LocalDate.now().plusDays(1)
        val validator = ValidatorLaterDayToday(date)
        Assertions.assertFalse(validator.isValid())
    }
}