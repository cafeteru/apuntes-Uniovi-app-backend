package es.uniovi.apuntesuniovi.controllers.impl

import es.uniovi.apuntesuniovi.controllers.UserController
import es.uniovi.apuntesuniovi.controllers.impl.subjects.SaveUser
import es.uniovi.apuntesuniovi.controllers.impl.users.FindAllUsers
import es.uniovi.apuntesuniovi.infrastructure.constants.UrlUtils
import es.uniovi.apuntesuniovi.log.LogService
import es.uniovi.apuntesuniovi.servicies.ServiceFactory
import es.uniovi.apuntesuniovi.servicies.dtos.entities.UserDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(UrlUtils.users)
class UserControllerImpl @Autowired constructor(
        private val serviceFactory: ServiceFactory
) : UserController {
    private val logService = LogService(this.javaClass)

    @GetMapping(UrlUtils.findAll)
    override fun findAll(): ResponseEntity<List<UserDto>> {
        logService.info("findAll() - start")
        val result = FindAllUsers(serviceFactory).execute()
        logService.info("findAll() - end")
        return ResponseEntity(result, HttpStatus.OK)
    }

    @PostMapping(UrlUtils.save)
    override fun save(@RequestBody json: String?): ResponseEntity<List<UserDto>> {
        logService.info("save(json: ${logService.formatJson(json)}) - start")
        val result = SaveUser(serviceFactory, json).execute()
        logService.info("save(json:${logService.formatJson(json)}) - end")
        return ResponseEntity(result, HttpStatus.OK)
    }
}