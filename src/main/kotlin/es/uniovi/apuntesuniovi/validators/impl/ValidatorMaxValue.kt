package es.uniovi.apuntesuniovi.validators.impl

import io.github.cafeteru.validator_lib.Validator

/**
 * Valid value exceeds limit
 */
class ValidatorMaxValue<T : Comparable<T>>(
    private var value: T,
    private var maxValue: T
) : Validator {
    override fun isValid(): Boolean {
        return value <= maxValue
    }
}
