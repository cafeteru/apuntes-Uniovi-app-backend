package es.uniovi.apuntesuniovi.controllers.commands.users

import es.uniovi.apuntesuniovi.infrastructure.AbstractCommand
import es.uniovi.apuntesuniovi.services.UserService
import es.uniovi.apuntesuniovi.services.dtos.entities.UserDto

class FindUserById(
  private val userService: UserService,
  private val id: Long
) : AbstractCommand<UserDto>() {
  override fun execute(): UserDto {
    logService.info("execute() - start")
    val result = userService.findById(id)
    logService.info("execute() - end")
    return result
  }
}