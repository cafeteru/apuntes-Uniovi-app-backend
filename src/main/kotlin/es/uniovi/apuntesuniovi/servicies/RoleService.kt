package es.uniovi.apuntesuniovi.servicies

import es.uniovi.apuntesuniovi.servicies.dtos.entities.RoleDto

interface RoleService {
    fun findAll(): List<RoleDto>
    fun save(roleDto: RoleDto): List<RoleDto>
}