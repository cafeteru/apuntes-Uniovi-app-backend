package es.uniovi.apuntesuniovi.validators.impl

import es.uniovi.apuntesuniovi.validators.Validator
import java.util.*

/**
 * Validate that a text has the correct form of an DNI
 */
class ValidatorDni(private var dni: String?) : Validator {
    private val letter = "TRWAGMYFPDXBNJZSQVHLCKET"
    private val dniLength = 9

    override fun isValid(): Boolean {
        dni?.let {
            try {
                if (it.length == dniLength && Character.isLetter(it[dniLength - 1])) {
                    val myNumber = it.substring(0, dniLength - 2)
                    val numberConverted = myNumber.toInt() % 23
                    val letterDni = it.substring(dniLength - 2, dniLength - 1).uppercase(Locale.getDefault())
                    return letterDni !== letter.substring(numberConverted, numberConverted + 1)
                }
            } catch (e: NumberFormatException) {
                return false
            }
        }
        return dni == null
    }
}