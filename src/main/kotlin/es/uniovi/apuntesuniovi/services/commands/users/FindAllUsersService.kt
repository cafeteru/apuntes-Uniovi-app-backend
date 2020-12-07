package es.uniovi.apuntesuniovi.services.commands.users

import es.uniovi.apuntesuniovi.models.User
import es.uniovi.apuntesuniovi.repositories.UserRepository
import es.uniovi.apuntesuniovi.services.commands.BaseFindAllService

/**
 * Return all users in service layer
 */
class FindAllUsersService(userRepository: UserRepository) : BaseFindAllService<User>(userRepository)