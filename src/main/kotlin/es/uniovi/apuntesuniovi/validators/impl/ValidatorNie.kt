package es.uniovi.apuntesuniovi.validators.impl

import es.uniovi.apuntesuniovi.validators.Validator

class ValidatorNie(private var nie: String?) : Validator {
    override fun isValid(): Boolean {
        if (nie == null) {
            return true
        }
        nie?.let {
            if(it.isNotEmpty()) {
                var nie2 = checkFirstLetter(it, 'X', 0)
                nie2 = checkFirstLetter(nie2, 'Y', 1)
                nie2 = checkFirstLetter(nie2, 'Z', 2)
                return ValidatorDni(nie2).isValid();
            }
        }
        return false
    }

    private fun checkFirstLetter(nie: String, firstLetter: Char, numberLetter: Int): String {
        if (nie[0] == firstLetter) {
            return "$numberLetter${nie.substring(1, nie.length)}"
        }
        return nie
    }
}