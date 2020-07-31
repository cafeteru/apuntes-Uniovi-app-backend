package es.uniovi.apuntesuniovi.servicies.dtos.entities

data class RoleDto(
        var id: Long,
        var name: String,
        var active: Boolean,
        var isAdmin: Boolean
)