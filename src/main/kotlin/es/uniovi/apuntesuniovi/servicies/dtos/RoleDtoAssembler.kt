package es.uniovi.apuntesuniovi.servicies.dtos

import es.uniovi.apuntesuniovi.entities.Role
import es.uniovi.apuntesuniovi.servicies.dtos.entities.RoleDto

interface RoleDtoAssembler {
    fun entityToDto(entity: Role): RoleDto
    fun dtoToEntity(dto: RoleDto): Role
    fun listToDto(entityList: List<Role>): List<RoleDto>
    fun listToEntities(dtoList: List<RoleDto>): List<Role>
}