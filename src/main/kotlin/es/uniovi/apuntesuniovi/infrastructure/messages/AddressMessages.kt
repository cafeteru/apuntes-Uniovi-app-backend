package es.uniovi.apuntesuniovi.infrastructure.messages

/**
 * Defines the messages that the address exceptions return
 */
object AddressMessages {
  const val LIMIT_CITY = "error.address.limit.city"
  const val LIMIT_COUNTRY = "error.address.limit.country"
  const val LIMIT_POSTAL_CODE = "error.address.limit.postal.code"
  const val LIMIT_STREET = "error.address.limit.street"
}