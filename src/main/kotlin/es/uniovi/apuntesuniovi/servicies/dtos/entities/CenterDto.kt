package es.uniovi.apuntesuniovi.servicies.dtos.entities

import es.uniovi.apuntesuniovi.entities.Address

data class CenterDto(
    var id: Long?,
    var name: String?,
    var address: Address?
)