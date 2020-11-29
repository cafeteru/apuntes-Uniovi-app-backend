package es.uniovi.apuntesuniovi.controllers

import es.uniovi.apuntesuniovi.controllers.commands.subjects.FindAllSubjects
import es.uniovi.apuntesuniovi.controllers.commands.subjects.SaveSubject
import es.uniovi.apuntesuniovi.infrastructure.constants.Urls
import es.uniovi.apuntesuniovi.infrastructure.log.LogService
import es.uniovi.apuntesuniovi.servicies.ServiceFactory
import es.uniovi.apuntesuniovi.servicies.dtos.entities.SubjectDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

/**
 * Define subject endpoints
 */
@RestController
@RequestMapping(Urls.SUBJECT)
class SubjectController @Autowired constructor(private val serviceFactory: ServiceFactory) {
    private val logService = LogService(this.javaClass)

    /**
     * Returns all subjects registered in the system
     */
    @GetMapping(Urls.FIND_ALL)
    fun findAll(): ResponseEntity<List<SubjectDto>> {
        logService.info("findAll() - start")
        val result = FindAllSubjects(serviceFactory.getSubjects()).execute()
        logService.info("findAll() - end")
        return ResponseEntity(result, HttpStatus.OK)
    }

    /**
     * Add a new subject through a text string (JSON)
     */
    @PostMapping(Urls.SAVE)
    fun save(@RequestBody json: String): ResponseEntity<List<SubjectDto>> {
        logService.info("save(json: ${logService.formatJson(json)}) - start")
        val result = SaveSubject(serviceFactory.getSubjects(), json).execute()
        logService.info("save(json:${logService.formatJson(json)}) - end")
        return ResponseEntity(result, HttpStatus.OK)
    }
}