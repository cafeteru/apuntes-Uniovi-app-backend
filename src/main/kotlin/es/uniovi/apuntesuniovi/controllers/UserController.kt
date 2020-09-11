package es.uniovi.apuntesuniovi.controllers

import es.uniovi.apuntesuniovi.controllers.commands.users.FindAllUsers
import es.uniovi.apuntesuniovi.controllers.commands.users.SaveUser
import es.uniovi.apuntesuniovi.infrastructure.constants.Urls
import es.uniovi.apuntesuniovi.log.LogService
import es.uniovi.apuntesuniovi.servicies.ServiceFactory
import es.uniovi.apuntesuniovi.servicies.dtos.entities.UserDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

/**
 * Define user endpoints
 */
@RestController
@RequestMapping(Urls.USERS)
class UserController @Autowired constructor(private val serviceFactory: ServiceFactory) {
    private val logService = LogService(this.javaClass)

    /**
     * Returns all registered users in the system
     */
    @GetMapping(Urls.FIND_ALL)
    fun findAll(): ResponseEntity<List<UserDto>> {
        logService.info("findAll() - start")
        val result = FindAllUsers(serviceFactory.getUsers()).execute()
        logService.info("findAll() - end")
        return ResponseEntity(result, HttpStatus.OK)
    }

    /**
     * Add a new user through a text string (JSON)
     */
    @PostMapping(Urls.SAVE)
    fun save(@RequestBody json: String): ResponseEntity<List<UserDto>> {
        logService.info("save(json: ${logService.formatJson(json)}) - start")
        val result = SaveUser(serviceFactory.getUsers(), json).execute()
        logService.info("save(json:${logService.formatJson(json)}) - end")
        return ResponseEntity(result, HttpStatus.OK)
    }
}