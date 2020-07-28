package es.uniovi.apuntesuniovi.servicies.dtos.impl

import es.uniovi.apuntesuniovi.entities.User
import es.uniovi.apuntesuniovi.log.LogService
import es.uniovi.apuntesuniovi.servicies.dates.DateService
import es.uniovi.apuntesuniovi.servicies.dtos.UserDtoAssembler
import es.uniovi.apuntesuniovi.servicies.dtos.RoleDtoAssembler
import es.uniovi.apuntesuniovi.servicies.dtos.entities.UserDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
import java.util.function.Consumer

@Service
class UserAssemblerDtoImpl @Autowired constructor(
        private val roleDtoAssembler: RoleDtoAssembler
) : UserDtoAssembler {
    private val logService = LogService(this.javaClass)

    override fun entityToDto(entity: User): UserDto {
        logService.info("entityToDto(entity: ${entity}) - start")
        val role = entity.role?.let { roleDtoAssembler.entityToDto(it) }
        val result = UserDto(
                id = entity.id,
                name = entity.name,
                surname = entity.surname,
                email = entity.email,
                phone = entity.phone,
                active = entity.active,
                img = entity.img,
                birthDate = DateService.dateToString(entity.birthDate),
                username = entity.username,
                password = entity.password,
                role = role,
                identificationType = entity.identificationType.toString(),
                numberIdentification = entity.numberIdentification)
        logService.info("entityToDto(entity: ${entity}) - end")
        return result
    }

    override fun dtoToEntity(dto: UserDto): User {
        logService.info("dtoToEntity(dto: ${dto}) - start")
        val role = dto.role?.let { roleDtoAssembler.dtoToEntity(it) }
        val result = User(id = dto.id,
                name = dto.name,
                surname = dto.surname,
                email = dto.email,
                phone = dto.phone,
                active = dto.active,
                img = dto.img,
                birthDate = dto.birthDate,
                username = dto.username,
                password = dto.password,
                identificationType = dto.identificationType,
                numberIdentification = dto.numberIdentification,
                role = role)
        logService.info("dtoToEntity(dto: ${dto}) - end")
        return result
    }

    override fun listToDto(entityList: List<User>): List<UserDto> {
        logService.info("listToDto(entityList: ${entityList}) - start")
        val dtoList: MutableList<UserDto> = ArrayList()
        entityList.forEach(Consumer { entity: User -> dtoList.add(entityToDto(entity)) })
        logService.info("listToDto(entityList: ${entityList}) - end")
        return dtoList
    }

    override fun listToEntities(dtoList: List<UserDto>): List<User> {
        logService.info("listToEntities(dtoList: ${dtoList}) - start")
        val entityList: MutableList<User> = ArrayList()
        dtoList.forEach(Consumer { dto: UserDto -> entityList.add(dtoToEntity(dto)) })
        logService.info("listToEntities(dtoList: ${dtoList}) - end")
        return entityList
    }
}