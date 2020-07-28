package es.uniovi.apuntesuniovi.controllers.impl

import es.uniovi.apuntesuniovi.controllers.RoleController
import es.uniovi.apuntesuniovi.controllers.impl.roles.FindAllRoles
import es.uniovi.apuntesuniovi.controllers.impl.roles.SaveRole
import es.uniovi.apuntesuniovi.infrastructure.constants.UrlUtils
import es.uniovi.apuntesuniovi.log.LogService
import es.uniovi.apuntesuniovi.servicies.ServiceFactory
import es.uniovi.apuntesuniovi.servicies.dtos.entities.RoleDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(UrlUtils.roles)
class RoleControllerImpl @Autowired constructor(
        private val serviceFactory: ServiceFactory
) : RoleController {
    private val logService = LogService(this.javaClass)

    @GetMapping(UrlUtils.findAll)
    override fun findAll(): ResponseEntity<List<RoleDto>> {
        logService.info("findAll() - start")
        val result = FindAllRoles(serviceFactory).execute()
        logService.info("findAll() - end")
        return ResponseEntity(result, HttpStatus.OK)
    }

    @PostMapping(UrlUtils.save)
    override fun save(@RequestBody json: String?): ResponseEntity<List<RoleDto>> {
        logService.info("save(json: ${logService.formatJson(json)}) - start")
        val result = SaveRole(serviceFactory, json).execute()
        logService.info("save(json:${logService.formatJson(json)}) - end")
        return ResponseEntity(result, HttpStatus.OK)
    }
}