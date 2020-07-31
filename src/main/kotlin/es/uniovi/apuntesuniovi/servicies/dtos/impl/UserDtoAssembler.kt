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
                role = roleDtoAssembler.entityToDto(entity.role),
                identificationType = entity.identificationType.toString(),
                numberIdentification = entity.numberIdentification)
        logService.info("entityToDto(entity: ${entity}) - end")
        return result
    }

    override fun dtoToEntity(dto: UserDto): User {
        logService.info("dtoToEntity(dto: ${dto}) - start")
        val result = User()
        result.id = dto.id
        result.name = dto.name
        result.surname = dto.surname
        result.email = dto.email
        result.phone = dto.phone
        result.active = dto.active
        result.img = dto.img
        result.birthDate = DateService.stringToDate(dto.birthDate).toDate()
        result.username = dto.username
        result.password = dto.password
        result.identificationType = result.setIdentificationType(dto.identificationType)
        result.numberIdentification = dto.numberIdentification
        result.role = roleDtoAssembler.dtoToEntity(dto.role)
        logService.info("dtoToEntity(dto: ${dto}) - end")
        return result
    }
}