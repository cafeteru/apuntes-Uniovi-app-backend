package es.uniovi.apuntesuniovi.controllers

import es.uniovi.apuntesuniovi.controllers.commands.subjects.FindAllSubjects
import es.uniovi.apuntesuniovi.controllers.commands.subjects.SaveSubject
import es.uniovi.apuntesuniovi.infrastructure.log.LogService
import es.uniovi.apuntesuniovi.servicies.SubjectService
import es.uniovi.apuntesuniovi.servicies.dtos.entities.SubjectDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

/**
 * Define subject endpoints
 */
@RestController
@RequestMapping("/subjects")
class SubjectController @Autowired constructor(
    private val subjectService: SubjectService
) {
    private val logService = LogService(this.javaClass)

    /**
     * Returns all subjects registered in the system
     */
    @GetMapping("")
    fun findAll(): ResponseEntity<List<SubjectDto>> {
        logService.info("findAll() - start")
        val result = FindAllSubjects(subjectService).execute()
        logService.info("findAll() - end")
        return ResponseEntity(result, HttpStatus.OK)
    }

    /**
     * Add a new subject through a text string (JSON)
     */
    @PostMapping("/create")
    fun save(@RequestBody json: String): ResponseEntity<List<SubjectDto>> {
        logService.info("save(json: ${logService.formatJson(json)}) - start")
        val result = SaveSubject(subjectService, json).execute()
        logService.info("save(json:${logService.formatJson(json)}) - end")
        return ResponseEntity(result, HttpStatus.OK)
    }
}