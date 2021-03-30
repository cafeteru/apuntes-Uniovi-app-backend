package es.uniovi.apuntesuniovi.validators

import java.util.*

/**
 * Check a list of validators
 */
abstract class ValidatorComposite : Validator {
    protected val validators = ArrayList<Validator>()

    /**
     * Add validator to the list
     */
    fun add(validator: Validator) {
        this.validators.add(validator)
    }
}
