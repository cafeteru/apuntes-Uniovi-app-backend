package es.uniovi.apuntesuniovi.infrastructure.constants.database

/**
 * Indicates the size limits of the address in the database
 */
object AddressLimits {
    const val STREET = 100
    const val CITY = 100
    const val POSTAL_CODE = 10
    const val COUNTRY = 50
}