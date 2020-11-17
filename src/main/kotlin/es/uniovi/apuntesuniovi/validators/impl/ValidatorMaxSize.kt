package es.uniovi.apuntesuniovi.validators.impl

import es.uniovi.apuntesuniovi.validators.Validator

class ValidatorMaxSize(
        private var text: String?,
        private var maxSize: Int
) : Validator {
    override fun isValid(): Boolean {
        text?.let {
            return it.length <= maxSize
        }
        return text == null;
    }
}