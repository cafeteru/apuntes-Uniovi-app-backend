package es.uniovi.apuntesuniovi.servicies.dtos.impl

import es.uniovi.apuntesuniovi.entities.User
import es.uniovi.apuntesuniovi.servicies.dtos.AbstractDtoAssembler
import es.uniovi.apuntesuniovi.servicies.dtos.entities.UserDto
import org.springframework.stereotype.Service

@Service
class UserDtoAssembler : AbstractDtoAssembler<User, UserDto>() {
    override fun entityToDto(user: User): UserDto {
        logService.info("entityToDto(user: User) - start")
        val result = UserDto(
                id = user.id,
                name = user.name,
                surname = user.surname,
                email = user.email,
                phone = user.phone,
                active = user.active,
                img = user.img,
                birthDate = user.birthDate,
                username = user.username,
                password = user.password,
                role = user.role.toString(),
                identificationType = user.identificationType.toString(),
                numberIdentification = user.numberIdentification)
        logService.info("entityToDto(user: User) - end")
        return result
    }

    override fun dtoToEntity(dto: UserDto): User {
        logService.info("dtoToEntity(userDto: UserDto) - start")
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
}