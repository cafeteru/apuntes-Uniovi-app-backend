package es.uniovi.apuntesuniovi.services.dtos.entities

import es.uniovi.apuntesuniovi.models.Address

/**
 * Data Transfer Object of centers
 */
data class CenterDto(
  var id: Long?,
  var name: String,
  var address: Address?
)