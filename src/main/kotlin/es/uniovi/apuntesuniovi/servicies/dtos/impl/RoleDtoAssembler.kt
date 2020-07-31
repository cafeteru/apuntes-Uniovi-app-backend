package es.uniovi.apuntesuniovi.servicies.dtos.impl

import es.uniovi.apuntesuniovi.entities.Role
import es.uniovi.apuntesuniovi.servicies.dtos.AbstractDtoAssembler
import es.uniovi.apuntesuniovi.servicies.dtos.entities.RoleDto
import org.springframework.stereotype.Service

@Service
class RoleDtoAssembler : AbstractDtoAssembler<Role, RoleDto>() {
    override fun entityToDto(entity: Role): RoleDto {
        logService.info("entityToDto(entity: ${entity}) - start")
        val result = RoleDto(
                id = entity.id,
                name = entity.name,
                active = entity.active,
                isAdmin = entity.isAdmin)
        logService.info("entityToDto(entity: ${entity}) - end")
        return result
    }

    override fun dtoToEntity(dto: RoleDto): Role {
        logService.info("dtoToEntity(dto: ${dto}) - start")
        val result = Role()
        result.id = dto.id
        result.name = dto.name
        result.active = dto.active
        result.isAdmin = dto.isAdmin
        logService.info("dtoToEntity(dto: ${dto}) - end")
        return result
    }
}