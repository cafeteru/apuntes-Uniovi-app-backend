package es.uniovi.apuntesuniovi.validators.impl

import io.github.cafeteru.validator_lib.Validator

/**
 * Validate that a text don´t exceed a specific length
 */
class ValidatorMaxLength(
    private var text: String?,
    private var maxSize: Int
) : Validator {
    override fun isValid(): Boolean {
        text?.let {
            if (it.isEmpty()) {
                return false
            }
            return it.length <= maxSize
        }
        return true
    }
}
