package es.uniovi.apuntesuniovi.controllers.commands.users

import es.uniovi.apuntesuniovi.controllers.commands.AbstractFindAll
import es.uniovi.apuntesuniovi.services.UserService
import es.uniovi.apuntesuniovi.services.dtos.entities.UserDto

/**
 * Return all users in controller layer
 */
class FindAllUsers(userService: UserService) : AbstractFindAll<UserDto>(userService)