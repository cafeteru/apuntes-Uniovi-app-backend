package es.uniovi.apuntesuniovi.services.commands.users

import es.uniovi.apuntesuniovi.models.User
import es.uniovi.apuntesuniovi.repositories.UserRepository
import es.uniovi.apuntesuniovi.services.commands.BaseFindAllService
import org.springframework.data.domain.Pageable

/**
 * Return all users in service layer
 */
class FindAllUsers(
  userRepository: UserRepository,
  pageable: Pageable
) : BaseFindAllService<User>(userRepository, pageable)