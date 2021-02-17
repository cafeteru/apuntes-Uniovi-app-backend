package es.uniovi.apuntesuniovi.controllers

import es.uniovi.apuntesuniovi.infrastructure.log.LogService
import es.uniovi.apuntesuniovi.services.UserService
import es.uniovi.apuntesuniovi.services.dtos.entities.UserDto
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
   */
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @PostMapping(value = ["/create"], consumes = [MediaType.APPLICATION_JSON_VALUE])
  @ApiOperation(value = "Create a new user")
  fun create(@Valid @RequestBody userDto: UserDto): ResponseEntity<UserDto> {
    logService.info("save(json: String) - start")
    val result = userService.create(userDto)
    logService.info("save(json: String) - end")
    return ResponseEntity(result, HttpStatus.OK)
  }

  /**
   * Returns all registered users
   *
   * @param userDto User to apply filters
   * @param pageable Pageable
   */
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @PostMapping("")
  @ApiOperation("Returns all registered users")
  fun findAll(
    @RequestBody(required = false) userDto: UserDto?,
    pageable: Pageable
  ): ResponseEntity<Page<UserDto>> {
    logService.info("findAll() - start")
    val result = userService.findAll(userDto, pageable)
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
  @ApiOperation( "Return a user by id")
  fun findById(@PathVariable id: Long): ResponseEntity<UserDto> {
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
  @ApiOperation( "Change a user's language")
  fun changeLanguage(@PathVariable language: String, principal: Principal): ResponseEntity<Boolean> {
    logService.info("changeLanguage(language: ${language}) - start")
    userService.changeLanguage(principal.name, language)
    logService.info("changeLanguage(language: ${language}) - end")
    return ResponseEntity<Boolean>(HttpStatus.OK)
  }
}