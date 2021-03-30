package es.uniovi.apuntesuniovi.validators.impl

import es.uniovi.apuntesuniovi.validators.Validator

/**
 * Valid value exceeds limit
 */
class ValidatorMinValue<T : Comparable<T>>(
    private var value: T,
    private var minValue: T
) : Validator {
    override fun isValid(): Boolean {
        return value >= minValue
    }
}