package es.uniovi.apuntesuniovi.controllers

import es.uniovi.apuntesuniovi.infrastructure.log.LogService
import es.uniovi.apuntesuniovi.models.User
import es.uniovi.apuntesuniovi.services.UserService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import java.security.Principal
import javax.validation.Valid


/**
 * Define user`s endpoints
 */
@RestController
@RequestMapping("/users")
@Api(tags = ["Users"])
class UserController @Autowired constructor(
  private val userService: UserService
) {
  private val logService = LogService(this.javaClass)

  /**
   * Create a new user
   *
   * @param user User to create
   */
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @PostMapping(value = ["/create"], consumes = [MediaType.APPLICATION_JSON_VALUE])
  @ApiOperation(value = "Create a new user")
  fun create(
    @ApiParam(name = "user", value = "User to create") @Valid @RequestBody user: User
  ): ResponseEntity<User> {
    logService.info("save(user: user) - start")
    val result = userService.create(user)
    logService.info("save(user: user) - end")
    return ResponseEntity(result, HttpStatus.OK)
  }

  /**
   * Create a new user
   *
   * @param id User´s id
   * @param user User to update
   */
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @PutMapping(value = ["/{id}"], consumes = [MediaType.APPLICATION_JSON_VALUE])
  @ApiOperation(value = "Update a user")
  fun update(
    @ApiParam(name = "id", value = "User´s id") @PathVariable id: Long,
    @ApiParam(name = "user", value = "User to update") @Valid @RequestBody user: User
  ): ResponseEntity<User> {
    logService.info("update(id: ${id}, user: user) - start")
    val result = userService.update(id, user)
    logService.info("update(id: ${id}, user: user) - end")
    return ResponseEntity(result, HttpStatus.OK)
  }

  /**
   * Returns all registered users
   *
   * @param user User to apply filters
   * @param pageable Pageable
   */
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @PostMapping("")
  @ApiOperation("Returns all registered users")
  fun findAll(
    @ApiParam(name = "pageable", value = "Pageable") pageable: Pageable,
    @ApiParam(name = "user", value = "User to apply filters") @RequestBody(required = false) user: User?
  ): ResponseEntity<Page<User>> {
    logService.info("findAll() - start")
    val result = userService.findAll(pageable, user)
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
  @ApiOperation("Return a user by id")
  fun findById(
    @ApiParam(name = "id", value = "User´s id") @PathVariable id: Long
  ): ResponseEntity<User> {
    logService.info("findById(id: ${id}) - start")
    val result = userService.findById(id)
    logService.info("findById(id: ${id}) - end")
    return ResponseEntity(result, HttpStatus.OK)
  }

  /**
   * Change a user's language
   *
   * @param language Selected language
   * @param principal User sending the request
   */
  @PreAuthorize("isAuthenticated()")
  @RequestMapping(path = ["/lang/{language}"], method = [RequestMethod.HEAD])
  @ApiOperation("Change a user's language")
  fun changeLanguage(
    @ApiParam(name = "language", value = "Selected language") @PathVariable language: String,
    principal: Principal
  ): ResponseEntity<Boolean> {
    logService.info("changeLanguage(language: ${language}) - start")
    var status = HttpStatus.BAD_REQUEST
    if (userService.changeLanguage(principal.name, language)) {
      status = HttpStatus.OK
    }
    logService.info("changeLanguage(language: ${language}) - end")
    return ResponseEntity<Boolean>(status)
  }

  /**
   * Change the value active of a user
   *
   * @param id User´s id
   * @param active New value to user´s active
   */
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @PatchMapping("/disable/{id}/{active}")
  @ApiOperation("Change the value active of a user")
  fun disable(
    @ApiParam(name = "id", value = "User´s id") @PathVariable id: Long,
    @ApiParam(name = "active", value = "New value to user´s active") @PathVariable active: Boolean
  ): ResponseEntity<User> {
    logService.info("disable(id: ${id}, active: ${active}) - start")
    val user = userService.disable(id, active)
    logService.info("disable(id: ${id}, active: ${active}) - end")
    return ResponseEntity(user, HttpStatus.OK)
  }

  /**
   * Delete a user
   *
   * @param id User´s id
   */
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @DeleteMapping("/{id}")
  @ApiOperation("Delete a user")
  fun delete(
    @ApiParam(name = "id", value = "User´s id") @PathVariable id: Long
  ): ResponseEntity<Boolean> {
    logService.info("delete(id: ${id}) - start")
    val user = userService.delete(id)
    logService.info("delete(id: ${id}) - end")
    return ResponseEntity(user, HttpStatus.OK)
  }
}