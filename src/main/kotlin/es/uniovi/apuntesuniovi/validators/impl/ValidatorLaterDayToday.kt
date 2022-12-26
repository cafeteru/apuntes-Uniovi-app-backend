package es.uniovi.apuntesuniovi.validators.impl

import io.github.cafeteru.validator_lib.Validator
import java.time.LocalDate

/**
 * Validate that a date isn´t later than the current day
 */
class ValidatorLaterDayToday(private var date: LocalDate?) : Validator {
    override fun isValid(): Boolean {
        date?.let {
            val aux = LocalDate.now()
            return it.isBefore(aux) || it.isEqual(aux)
        }
        return true
    }
}
