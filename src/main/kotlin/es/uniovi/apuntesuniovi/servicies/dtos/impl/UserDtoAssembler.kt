package es.uniovi.apuntesuniovi.servicies.dtos.impl

import es.uniovi.apuntesuniovi.entities.User
import es.uniovi.apuntesuniovi.servicies.dates.DateService
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
                birthDate = DateService.dateToString(user.birthDate),
                username = user.username,
                password = user.password,
                role = user.role.toString(),
                identificationType = user.identificationType.toString(),
                numberIdentification = user.numberIdentification)
        logService.info("entityToDto(user: User) - end")
        return result
    }

    override fun dtoToEntity(userDto: UserDto): User {
        logService.info("dtoToEntity(userDto: UserDto) - start")
        val result = User()
        result.id = userDto.id
        result.name = userDto.name
        result.surname = userDto.surname
        result.email = userDto.email
        result.phone = userDto.phone
        result.active = userDto.active
        result.img = userDto.img
        result.birthDate = DateService.stringToDate(userDto.birthDate).toDate()
        result.username = userDto.username
        result.password = userDto.password
        result.setIdentificationType(userDto.identificationType)
        result.numberIdentification = userDto.numberIdentification
        result.setRole(userDto.role)
        logService.info("dtoToEntity(userDto: UserDto) - end")
        return result
    }
}