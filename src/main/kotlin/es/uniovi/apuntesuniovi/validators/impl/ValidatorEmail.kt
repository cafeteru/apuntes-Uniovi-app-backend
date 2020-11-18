package es.uniovi.apuntesuniovi.validators.impl

import es.uniovi.apuntesuniovi.validators.Validator
import java.util.regex.Matcher
import java.util.regex.Pattern

class ValidatorEmail(private var email: String?) : Validator {
    override fun isValid(): Boolean {
        email?.let {
            val pattern: Pattern = Pattern
                    .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
            val mather: Matcher = pattern.matcher(it)
            return mather.find()
        }
        return email == null
    }
}