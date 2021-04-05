package es.uniovi.apuntesuniovi.dtos.entities

data class AddressDto(
    val id: Long?,
    var street: String?,
    var city: String?,
    var postalCode: String?,
    var country: String?
)
