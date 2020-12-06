package es.uniovi.apuntesuniovi.services.commands.users

import es.uniovi.apuntesuniovi.models.User
import es.uniovi.apuntesuniovi.repositories.UserRepository
import es.uniovi.apuntesuniovi.services.commands.AbstractFindById

/**
 * Return user by id in service layer
 */
class FindUserByIdService(
    userRepository: UserRepository,
    id: Long
) : AbstractFindById<User>(userRepository, id)