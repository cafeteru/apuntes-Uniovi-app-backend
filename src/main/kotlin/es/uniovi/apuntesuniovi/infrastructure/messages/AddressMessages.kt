package es.uniovi.apuntesuniovi.infrastructure.messages

/**
 * Defines the messages that the address exceptions return
 */
object AddressMessages {
    const val LIMIT_STREET = "error.limit.address.street"
    const val LIMIT_CITY = "error.limit.address.city"
    const val LIMIT_POSTAL_CODE = "error.limit.address.postal.code"
    const val LIMIT_COUNTRY = "error.limit.address.country"
}