package es.uniovi.apuntesuniovi.controllers

import es.uniovi.apuntesuniovi.dtos.entities.UserDto
import es.uniovi.apuntesuniovi.infrastructure.log.LogService
import es.uniovi.apuntesuniovi.services.UserService
import es.uniovi.apuntesuniovi.statistics.UserStatistics
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
     * @param userDto User to create
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(value = ["/create"], consumes = [MediaType.APPLICATION_JSON_VALUE])
    @ApiOperation(value = "Create a new user")
    fun create(
        @ApiParam(name = "userDto", value = "User to create") @Valid @RequestBody userDto: UserDto
    ): ResponseEntity<UserDto> {
        logService.info("save(userDto: UserDto) - start")
        val result = userService.create(userDto)
        logService.info("save(userDto: UserDto) - end")
        return ResponseEntity(result, HttpStatus.OK)
    }

    /**
     * Create a new user
     *
     * @param id User´s id
     * @param userDto User to update
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping(value = ["/{id}"], consumes = [MediaType.APPLICATION_JSON_VALUE])
    @ApiOperation(value = "Update a user")
    fun update(
        @ApiParam(name = "id", value = "User´s id") @PathVariable id: Long,
        @ApiParam(name = "userDto", value = "User to update") @Valid @RequestBody userDto: UserDto
    ): ResponseEntity<UserDto> {
        logService.info("update(id: ${id}, userDto: UserDto) - start")
        val result = userService.update(id, userDto)
        logService.info("update(id: ${id}, userDto: UserDto) - end")
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
        @ApiParam(name = "pageable", value = "Pageable") pageable: Pageable,
        @ApiParam(name = "userDto", value = "User to apply filters") @RequestBody(required = false) userDto: UserDto?
    ): ResponseEntity<Page<UserDto>> {
        logService.info("findAll() - start")
        val page = userService.findAll(pageable, userDto)
        var code = HttpStatus.OK
        if (page.isEmpty) {
            code = HttpStatus.NO_CONTENT
        }
        logService.info("findAll() - end")
        return ResponseEntity(page, code)
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
    ): ResponseEntity<UserDto> {
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
    ): ResponseEntity<UserDto> {
        logService.info("disable(id: ${id}, active: ${active}) - start")
        val userDto = userService.disable(id, active)
        logService.info("disable(id: ${id}, active: ${active}) - end")
        return ResponseEntity(userDto, HttpStatus.OK)
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
        val result = userService.delete(id)
        logService.info("delete(id: ${id}) - end")
        return ResponseEntity(result, HttpStatus.OK)
    }

    /**
     * Return user statistics
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/statistics")
    @ApiOperation("Return user statistics")
    fun getStatistics(): ResponseEntity<UserStatistics> {
        logService.info("getStatistics() - start")
        val userStatistics = userService.getStadistics()
        logService.info("getStatistics() - end")
        return ResponseEntity(userStatistics, HttpStatus.OK)
    }
}