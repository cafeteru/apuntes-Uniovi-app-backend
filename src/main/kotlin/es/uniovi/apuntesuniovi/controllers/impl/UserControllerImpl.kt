package es.uniovi.apuntesuniovi.controllers.impl

import es.uniovi.apuntesuniovi.controllers.UserController
import es.uniovi.apuntesuniovi.controllers.impl.users.FindAllUsers
import es.uniovi.apuntesuniovi.controllers.impl.users.SaveUser
import es.uniovi.apuntesuniovi.infrastructure.constants.Urls
import es.uniovi.apuntesuniovi.log.LogService
import es.uniovi.apuntesuniovi.servicies.ServiceFactory
import es.uniovi.apuntesuniovi.servicies.dtos.entities.UserDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Urls.USERS)
class UserControllerImpl @Autowired constructor(
        private val serviceFactory: ServiceFactory
) : UserController {
    private val logService = LogService(this.javaClass)

    @GetMapping(Urls.FIND_ALL)
    override fun findAll(): ResponseEntity<List<UserDto>> {
        logService.info("findAll() - start")
        val result = FindAllUsers(serviceFactory.getUsers()).execute()
        logService.info("findAll() - end")
        return ResponseEntity(result, HttpStatus.OK)
    }

    @PostMapping(Urls.SAVE)
    override fun save(@RequestBody json: String): ResponseEntity<List<UserDto>> {
        logService.info("save(json: ${logService.formatJson(json)}) - start")
        val result = SaveUser(serviceFactory.getUsers(), json).execute()
        logService.info("save(json:${logService.formatJson(json)}) - end")
        return ResponseEntity(result, HttpStatus.OK)
    }
}