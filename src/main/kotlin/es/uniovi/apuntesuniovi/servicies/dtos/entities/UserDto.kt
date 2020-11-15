package es.uniovi.apuntesuniovi.servicies.dtos.entities

import java.time.LocalDate

/**
 * Dto of the users
 */
data class UserDto(
        var id: Long,
        var name: String,
        var surname: String,
        var email: String,
        var phone: String,
        var active: Boolean,
        var img: String,
        var birthDate: LocalDate,
        var username: String?,
        var password: String,
        var role: String,
        var identificationType: String,
        var numberIdentification: String?
)