package es.uniovi.apuntesuniovi.servicies.dtos.impl

import es.uniovi.apuntesuniovi.entities.User
import es.uniovi.apuntesuniovi.servicies.dates.DateService
import es.uniovi.apuntesuniovi.servicies.dtos.AbstractDtoAssembler
import es.uniovi.apuntesuniovi.servicies.dtos.entities.UserDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserDtoAssembler @Autowired constructor(
        private val roleDtoAssembler: RoleDtoAssembler
) : AbstractDtoAssembler<User, UserDto>() {
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
}