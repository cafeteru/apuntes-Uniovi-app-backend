package es.uniovi.apuntesuniovi.validators.impl

import es.uniovi.apuntesuniovi.validators.Validator

/**
 * Validate that a id is valid
 */
class ValidatorId(private var id: Long?) : Validator {
  override fun isValid(): Boolean {
    id?.let {
      return it > 0
    }
    return false
  }
}