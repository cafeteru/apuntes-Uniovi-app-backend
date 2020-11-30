package es.uniovi.apuntesuniovi.servicies.dtos.impl

import es.uniovi.apuntesuniovi.entities.User
import es.uniovi.apuntesuniovi.infrastructure.exceptions.messages.UserMessages
import es.uniovi.apuntesuniovi.servicies.dtos.AbstractDtoAssembler
import es.uniovi.apuntesuniovi.servicies.dtos.entities.UserDto
import org.springframework.stereotype.Service
import java.lang.IllegalArgumentException

@Service
class UserDtoAssembler : AbstractDtoAssembler<User, UserDto>() {
    override fun entityToDto(entity: User?): UserDto {
        logService.info("entityToDto(user: User) - start")
        entity?.let {
            val result = UserDto(
                    id = it.id,
                    name = entity.name,
                    surname = entity.surname,
                    email = entity.email,
                    phone = entity.phone,
                    active = entity.active,
                    img = entity.img,
                    birthDate = entity.birthDate,
                    username = entity.username,
                    password = entity.password,
                    role = entity.role.toString(),
                    identificationType = entity.identificationType.toString(),
                    numberIdentification = entity.numberIdentification)
            logService.info("entityToDto(user: User) - end")
            return result
        }
        throw IllegalArgumentException(UserMessages.NULL_USER)
    }

    override fun dtoToEntity(dto: UserDto?): User {
        logService.info("dtoToEntity(userDto: UserDto) - start")
        dto?.let {
            val result = User()
            result.id = dto.id
            result.name = dto.name
            result.surname = dto.surname
            result.email = dto.email
            result.phone = dto.phone
            result.active = dto.active
            result.img = dto.img
            result.birthDate = dto.birthDate
            result.username = dto.username
            result.password = dto.password
            result.setIdentificationType(dto.identificationType)
            result.numberIdentification = dto.numberIdentification
            result.setRole(dto.role)
            logService.info("dtoToEntity(userDto: UserDto) - end")
            return result
        }
        throw IllegalArgumentException(UserMessages.NULL_USER)
    }
}