package es.uniovi.apuntesuniovi.validators

/**
 * Interface to define data validators
 */
interface Validator {
    /**
     * Check the data entered
     */
    fun isValid(): Boolean
}