package es.uniovi.apuntesuniovi.dtos.entities

import java.time.LocalDate

/**
 * Data Transfer Object of users
 */
data class UserDto(
    var id: Long?,
    var name: String?,
    var surname: String?,
    var email: String?,
    var phone: String?,
    var active: Boolean?,
    var img: String?,
    var birthDate: LocalDate?,
    var username: String?,
    var password: String?,
    var role: String?,
    var language: String,
    var identificationType: String?,
    var numberIdentification: String?,
    var address: AddressDto?
)