package es.uniovi.apuntesuniovi.controllers

import es.uniovi.apuntesuniovi.controllers.commands.roles.FindAllRoles
import es.uniovi.apuntesuniovi.controllers.commands.roles.SaveRole
import es.uniovi.apuntesuniovi.infrastructure.constants.Urls
import es.uniovi.apuntesuniovi.log.LogService
import es.uniovi.apuntesuniovi.servicies.ServiceFactory
import es.uniovi.apuntesuniovi.servicies.dtos.entities.RoleDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

/**
 * Define role endpoints
 */
@RestController
@RequestMapping(Urls.ROLES)
class RoleController @Autowired constructor(
        private val serviceFactory: ServiceFactory
) {
    private val logService = LogService(this.javaClass)

    /**
     * Returns all the roles registered in the system
     */
    @GetMapping(Urls.FIND_ALL)
    fun findAll(): ResponseEntity<List<RoleDto>> {
        logService.info("findAll() - start")
        val result = FindAllRoles(serviceFactory.getRoles()).execute()
        logService.info("findAll() - end")
        return ResponseEntity(result, HttpStatus.OK)
    }

    /**
     * Add a new role through a text string (JSON)
     */
    @PostMapping(Urls.SAVE)
    fun save(@RequestBody json: String): ResponseEntity<List<RoleDto>> {
        logService.info("save(json: ${logService.formatJson(json)}) - start")
        val result = SaveRole(serviceFactory.getRoles(), json).execute()
        logService.info("save(json:${logService.formatJson(json)}) - end")
        return ResponseEntity(result, HttpStatus.OK)
    }
}