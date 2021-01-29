package es.uniovi.apuntesuniovi.controllers.commands.users

import es.uniovi.apuntesuniovi.infrastructure.AbstractCommand
import es.uniovi.apuntesuniovi.services.UserService
import es.uniovi.apuntesuniovi.services.dtos.entities.UserDto
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

/**
 * Return all users in controller layer
 */
class FindAllUsers(
  private val userService: UserService,
  private val pageable: Pageable
) : AbstractCommand<Page<UserDto>>() {
  override fun execute(): Page<UserDto> {
    logService.info("execute() - start")
    val result = userService.findAll(pageable)
    logService.info("execute() - end")
    return result
  }
}