package es.uniovi.apuntesuniovi.servicies.dtos.assemblers

import es.uniovi.apuntesuniovi.entities.User
import es.uniovi.apuntesuniovi.infrastructure.messages.UserMessages
import es.uniovi.apuntesuniovi.servicies.dtos.entities.UserDto
import org.springframework.stereotype.Service

/**
 * Define the entity and dto conversion methods of users
 */
@Service
class UserAssembler : AbstractDtoAssembler<User, UserDto>() {
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
                numberIdentification = entity.numberIdentification,
                address = entity.address
            )
            logService.info("entityToDto(user: User) - end")
            return result
        }
        logService.error("entityToDto(user: User) - error")
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
            result.address = dto.address
            logService.info("dtoToEntity(userDto: UserDto) - end")
            return result
        }
        logService.info("dtoToEntity(userDto: UserDto) - error")
        throw IllegalArgumentException(UserMessages.NULL_USER)
    }
}