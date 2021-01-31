package es.uniovi.apuntesuniovi.controllers

import es.uniovi.apuntesuniovi.controllers.commands.users.CreateUser
import es.uniovi.apuntesuniovi.controllers.commands.users.FindAllUsers
import es.uniovi.apuntesuniovi.controllers.commands.users.FindUserById
import es.uniovi.apuntesuniovi.infrastructure.log.LogService
import es.uniovi.apuntesuniovi.models.types.RoleType
import es.uniovi.apuntesuniovi.services.UserService
import es.uniovi.apuntesuniovi.services.dtos.entities.UserDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

/**
 * Define user endpoints
 */
@RestController
@RequestMapping("/users")
class UserController @Autowired constructor(
  private val userService: UserService
) {
  private val logService = LogService(this.javaClass)

  /**
   * Add a new user through a text string (JSON)
   */
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @PostMapping("/create")
  fun create(@RequestBody json: String): ResponseEntity<UserDto> {
    logService.info("save(json: String) - start")
    val result = CreateUser(userService, json).execute()
    logService.info("save(json: String) - end")
    return ResponseEntity(result, HttpStatus.OK)
  }

  /**
   * Returns all registered users
   */
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @GetMapping("")
  fun findAll(pageable: Pageable): ResponseEntity<Page<UserDto>> {
    logService.info("findAll() - start")
    val result = FindAllUsers(userService, pageable).execute()
    logService.info("findAll() - end")
    return ResponseEntity(result, HttpStatus.OK)
  }

  /**
   * Return a user by id
   *
   * @param id User´s id
   */
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @GetMapping("/{id}")
  fun findById(@PathVariable id: Long): ResponseEntity<UserDto> {
    logService.info("findById(id: ${id}) - start")
    val result = FindUserById(userService, id).execute()
    logService.info("findById(id: ${id}) - end")
    return ResponseEntity(result, HttpStatus.OK)
  }
}