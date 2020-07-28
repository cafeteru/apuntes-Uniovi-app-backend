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
                active = entity.active)
        logService.info("entityToDto(entity: ${entity}) - end")
        return result
    }

    override fun dtoToEntity(dto: RoleDto): Role {
        logService.info("dtoToEntity(dto: ${dto}) - start")
        val result = Role(
                id = dto.id,
                name = dto.name,
                active = dto.active)
        logService.info("dtoToEntity(dto: ${dto}) - end")
        return result
    }
}