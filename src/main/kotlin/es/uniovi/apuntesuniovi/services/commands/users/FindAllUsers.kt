package es.uniovi.apuntesuniovi.services.commands.users

import es.uniovi.apuntesuniovi.infrastructure.AbstractCommand
import es.uniovi.apuntesuniovi.models.User
import es.uniovi.apuntesuniovi.repositories.UserRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

/**
 * Return all users in service layer
 */
class FindAllUsers(
  private val userRepository: UserRepository,
  private val pageable: Pageable
) : AbstractCommand<Page<User>>() {
  override fun execute(): Page<User> {
    logService.info("execute() - start")
    val list = userRepository.findAll(pageable)
    logService.info("execute() - end")
    return list
  }
}