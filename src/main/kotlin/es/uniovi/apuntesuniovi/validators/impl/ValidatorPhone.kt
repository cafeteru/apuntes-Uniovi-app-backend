package es.uniovi.apuntesuniovi.validators.impl

import io.github.cafeteru.validator_lib.Validator
import java.util.regex.Matcher
import java.util.regex.Pattern

/**
 * Validate that a text has the correct form of an phone
 */
class ValidatorPhone(private var phone: String?) : Validator {
    override fun isValid(): Boolean {
        phone?.let {
            val pattern: Pattern = Pattern
                .compile("^[679][0-9]{8}$")
            val mather: Matcher = pattern.matcher(it)
            return mather.find()
        }
        return phone == null
    }
}
