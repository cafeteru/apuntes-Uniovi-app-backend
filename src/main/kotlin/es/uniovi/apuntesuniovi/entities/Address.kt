package es.uniovi.apuntesuniovi.entities

import es.uniovi.apuntesuniovi.infrastructure.constants.database.AddressLimits
import es.uniovi.apuntesuniovi.infrastructure.messages.AddressMessages
import es.uniovi.apuntesuniovi.validators.impl.ValidatorMaxLength
import javax.persistence.*

/**
 * Represents addresses
 */
@Entity
class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(length = AddressLimits.STREET)
    var street: String? = null
        set(value) {
            if (ValidatorMaxLength(value, AddressLimits.STREET).isValid()) {
                field = value
            } else {
                throw IllegalArgumentException(AddressMessages.LIMIT_STREET)
            }
        }

    @Column(length = AddressLimits.CITY)
    var city: String? = null
        set(value) {
            if (ValidatorMaxLength(value, AddressLimits.CITY).isValid()) {
                field = value
            } else {
                throw IllegalArgumentException(AddressMessages.LIMIT_CITY)
            }
        }

    @Column(length = AddressLimits.POSTAL_CODE)
    var postalCode: String? = null
        set(value) {
            if (ValidatorMaxLength(value, AddressLimits.POSTAL_CODE).isValid()) {
                field = value
            } else {
                throw IllegalArgumentException(AddressMessages.LIMIT_POSTAL_CODE)
            }
        }

    @Column(length = AddressLimits.COUNTRY)
    var country: String? = null
        set(value) {
            if (ValidatorMaxLength(value, AddressLimits.COUNTRY).isValid()) {
                field = value
            } else {
                throw IllegalArgumentException(AddressMessages.LIMIT_COUNTRY)
            }
        }
}