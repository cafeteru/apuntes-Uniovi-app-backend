package es.uniovi.apuntesuniovi.services.dtos.entities

import es.uniovi.apuntesuniovi.models.Address
import java.time.LocalDate
import javax.validation.constraints.NotNull

/**
 * Data Transfer Object of users
 */
data class UserDto(
  var id: Long?,
  var name: String?,
  var surname: String?,
  var email: String?,
  var phone: String?,
  var active: Boolean,
  var img: String?,
  var birthDate: LocalDate?,
  var username: String?,
  var password: String?,
  var role: String?,
  var language: String,
  var identificationType: String?,
  var numberIdentification: String?,
  var address: Address?
)