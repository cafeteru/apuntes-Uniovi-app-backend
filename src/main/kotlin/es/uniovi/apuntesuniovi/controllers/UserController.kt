package es.uniovi.apuntesuniovi.controllers

import es.uniovi.apuntesuniovi.controllers.commands.users.CreateUser
import es.uniovi.apuntesuniovi.controllers.commands.users.FindAllUsers
import es.uniovi.apuntesuniovi.models.User
import es.uniovi.apuntesuniovi.services.BaseService
import es.uniovi.apuntesuniovi.services.UserService
import es.uniovi.apuntesuniovi.services.dtos.entities.UserDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Define user endpoints
 */
@RestController
@RequestMapping("/users")
class UserController @Autowired constructor(
    private val userService: UserService
) : BaseController<User, UserDto>(userService) {

    override fun create(
        baseService: BaseService<User, UserDto>,
        json: String
    ): UserDto {
        return CreateUser(userService, json).execute()
    }

    override fun findAll(
        baseService: BaseService<User, UserDto>,
        pageable: Pageable
    ): Page<UserDto> {
        return FindAllUsers(userService, pageable).execute()
    }
}