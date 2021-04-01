package es.uniovi.apuntesuniovi.services.commands.users

import es.uniovi.apuntesuniovi.dtos.entities.UserDto
import es.uniovi.apuntesuniovi.models.User
import es.uniovi.apuntesuniovi.repositories.UserRepository
import es.uniovi.apuntesuniovi.repositories.builders.UserBuilder
import es.uniovi.apuntesuniovi.services.commands.BaseFindAllService
import org.springframework.data.domain.Pageable

/**
 * Return all users in service layer
 */
class FindAllUsers(
    userRepository: UserRepository,
    userDto: UserDto?,
    pageable: Pageable
) : BaseFindAllService<User, UserDto>(
    userRepository, UserBuilder(), userDto, pageable
)