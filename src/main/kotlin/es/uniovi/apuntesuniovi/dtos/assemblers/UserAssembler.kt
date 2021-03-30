package es.uniovi.apuntesuniovi.dtos.assemblers

import es.uniovi.apuntesuniovi.dtos.entities.UserDto
import es.uniovi.apuntesuniovi.infrastructure.messages.UserMessages
import es.uniovi.apuntesuniovi.models.User
import org.springframework.stereotype.Service

/**
 * Define the entity and dto conversion methods of users
 */
@Service
class UserAssembler : AbstractAssembler<User, UserDto>() {
    override fun entityToDto(entity: User?): UserDto {
        logService.info("entityToDto(user: User) - start")

        entity?.let {
            var identificationType: String? = null
            if (it.identificationType != null) {
                identificationType = it.identificationType.toString()
            }
            val dto = UserDto(
                id = it.id,
                name = it.name,
                surname = it.surname,
                email = it.email,
                phone = it.phone,
                active = it.active,
                img = it.img,
                birthDate = it.birthDate,
                username = it.username,
                password = it.password,
                role = it.role.toString(),
                identificationType = identificationType,
                numberIdentification = it.numberIdentification,
                address = it.address,
                language = it.language.toString()
            )
            logService.info("entityToDto(user: User) - end")
            return dto
        }
        throw IllegalArgumentException(UserMessages.NULL)
    }

    override fun dtoToEntity(dto: UserDto?): User {
        logService.info("dtoToEntity(userDto: UserDto) - start")
        dto?.let {
            val user = User()
            user.id = it.id
            user.name = it.name
            user.surname = it.surname
            user.email = it.email
            user.phone = it.phone
            user.active = it.active.toString().toBoolean()
            user.img = it.img
            user.birthDate = it.birthDate
            user.username = it.username
            user.password = it.password
            user.setIdentificationType(it.identificationType)
            user.numberIdentification = it.numberIdentification
            user.setRole(it.role)
            user.address = it.address
            user.setLanguage(it.language)
            logService.info("dtoToEntity(userDto: UserDto) - end")
            return user
        }
        throw IllegalArgumentException(UserMessages.NULL)
    }
}