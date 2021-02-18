package es.uniovi.apuntesuniovi.services.commands.users

import es.uniovi.apuntesuniovi.infrastructure.AbstractCommand
import es.uniovi.apuntesuniovi.models.User
import es.uniovi.apuntesuniovi.repositories.UserRepository
import es.uniovi.apuntesuniovi.repositories.builders.UserBuilder
import es.uniovi.apuntesuniovi.services.dtos.entities.UserDto
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

/**
 * Return all users in service layer
 */
class FindAllUsers(
  private val userRepository: UserRepository,
  private val userDto: UserDto?,
  private val pageable: Pageable
) : AbstractCommand<Page<User>>() {

  override fun execute(): Page<User> {
    logService.info("execute() - start")
    val filters = UserBuilder().createBuilder(userDto)
    val list = userRepository.findAll(filters, pageable)
    logService.info("execute() - end")
    return list
  }
}