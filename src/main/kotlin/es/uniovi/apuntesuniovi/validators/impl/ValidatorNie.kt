package es.uniovi.apuntesuniovi.validators.impl

import es.uniovi.apuntesuniovi.validators.Validator

/**
 * Validate that a text has the correct form of an NIE
 */
class ValidatorNie(private var nie: String?) : Validator {
    override fun isValid(): Boolean {
        nie?.let {
            if (it.isNotEmpty()) {
                var aux = checkFirstLetter(it, 'X', 0)
                aux = checkFirstLetter(aux, 'Y', 1)
                aux = checkFirstLetter(aux, 'Z', 2)
                return ValidatorDni(aux).isValid()
            }
        }
        return nie == null
    }

    private fun checkFirstLetter(nie: String, firstLetter: Char, numberLetter: Int): String {
        if (nie[0] == firstLetter) {
            return "$numberLetter${nie.substring(1, nie.length)}"
        }
        return nie
    }
}