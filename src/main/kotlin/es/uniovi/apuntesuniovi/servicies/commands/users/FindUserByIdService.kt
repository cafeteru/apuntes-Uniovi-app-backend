package es.uniovi.apuntesuniovi.servicies.commands.users

import es.uniovi.apuntesuniovi.entities.User
import es.uniovi.apuntesuniovi.repositories.UserRepository
import es.uniovi.apuntesuniovi.servicies.commands.AbstractFindById

/**
 * Return user by id in service layer
 */
class FindUserByIdService(
    userRepository: UserRepository,
    id: Long
) : AbstractFindById<User>(userRepository, id)