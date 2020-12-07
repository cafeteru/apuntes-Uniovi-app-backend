package es.uniovi.apuntesuniovi.controllers

import es.uniovi.apuntesuniovi.controllers.commands.users.CreateUser
import es.uniovi.apuntesuniovi.controllers.commands.users.FindAllUsers
import es.uniovi.apuntesuniovi.infrastructure.AbstractCommand
import es.uniovi.apuntesuniovi.services.BaseService
import es.uniovi.apuntesuniovi.services.UserService
import es.uniovi.apuntesuniovi.services.dtos.entities.UserDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Define user endpoints
 */
@RestController
@RequestMapping("/users")
class UserController @Autowired constructor(
    private val userService: UserService
) : BaseController<UserDto>(userService) {
 
    override fun getCreateCommand(baseService: BaseService<UserDto>, json: String): AbstractCommand<List<UserDto>> {
        return CreateUser(userService, json)
    }

    override fun getFindAllCommand(baseService: BaseService<UserDto>): AbstractCommand<List<UserDto>> {
        return FindAllUsers(userService)
    }
}