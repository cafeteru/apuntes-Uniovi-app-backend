package es.uniovi.apuntesuniovi.validators.impl

import es.uniovi.apuntesuniovi.validators.ValidatorComposite

/**
 * Check a list of validators
 */
class ValidatorCompositeAny : ValidatorComposite() {

    override fun isValid(): Boolean {
        for (validator in validators) {
            if (validator.isValid()) {
                return true
            }
        }
        return false
    }
}
