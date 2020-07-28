package es.uniovi.apuntesuniovi.servicies.dtos.impl

import es.uniovi.apuntesuniovi.entities.Role
import es.uniovi.apuntesuniovi.log.LogService
import es.uniovi.apuntesuniovi.servicies.dtos.RoleDtoAssembler
import es.uniovi.apuntesuniovi.servicies.dtos.entities.RoleDto
import org.springframework.stereotype.Service
import java.util.*
import java.util.function.Consumer

@Service
class RoleAssemblerDtoImpl : RoleDtoAssembler {
    private val logService = LogService(this.javaClass)

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

    override fun listToDto(entityList: List<Role>): List<RoleDto> {
        logService.info("listToDto(entityList: ${entityList}) - start")
        val dtoList: MutableList<RoleDto> = ArrayList()
        entityList.forEach(Consumer { entity -> dtoList.add(entityToDto(entity)) })
        logService.info("listToDto(entityList: ${entityList}) - end")
        return dtoList
    }

    override fun listToEntities(dtoList: List<RoleDto>): List<Role> {
        logService.info("listToEntities(dtoList: ${dtoList}) - start")
        val entityList: MutableList<Role> = ArrayList()
        dtoList.forEach(Consumer { dto -> entityList.add(dtoToEntity(dto)) })
        logService.info("listToEntities(dtoList: ${dtoList}) - end")
        return entityList
    }
}