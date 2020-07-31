package es.uniovi.apuntesuniovi.servicies.dtos.entities

/**
 * Dto of the roles
 */
data class RoleDto(
        var id: Long,
        var name: String,
        var active: Boolean,
        var isAdmin: Boolean
)