package es.uniovi.apuntesuniovi.validators.impl

import es.uniovi.apuntesuniovi.validators.Validator
import java.util.regex.Matcher
import java.util.regex.Pattern

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