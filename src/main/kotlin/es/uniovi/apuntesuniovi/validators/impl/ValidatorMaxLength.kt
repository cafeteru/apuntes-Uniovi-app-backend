package es.uniovi.apuntesuniovi.validators.impl

import es.uniovi.apuntesuniovi.validators.Validator

/**
 * Validate that a text don´t exceed a specific length
 */
class ValidatorMaxLength(
        private var text: String?,
        private var maxSize: Int
) : Validator {
    override fun isValid(): Boolean {
        text?.let {
            return it.length <= maxSize
        }
        return text == null
    }
}