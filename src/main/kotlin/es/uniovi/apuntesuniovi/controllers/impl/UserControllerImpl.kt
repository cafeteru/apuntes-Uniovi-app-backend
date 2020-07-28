package es.uniovi.apuntesuniovi.controllers.impl

import es.uniovi.apuntesuniovi.controllers.SubjectController
import es.uniovi.apuntesuniovi.controllers.impl.subjects.FindAllSubjects
import es.uniovi.apuntesuniovi.controllers.impl.subjects.SaveSubject
import es.uniovi.apuntesuniovi.infrastructure.constants.UrlUtils
import es.uniovi.apuntesuniovi.log.LogService
import es.uniovi.apuntesuniovi.servicies.ServiceFactory
import es.uniovi.apuntesuniovi.servicies.dtos.entities.SubjectDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(UrlUtils.subject)
class SubjectControllerImpl @Autowired constructor(
        private val serviceFactory: ServiceFactory
) : SubjectController {
    private val logService = LogService(this.javaClass)

    @GetMapping(UrlUtils.findAll)
    override fun findAll(): ResponseEntity<List<SubjectDto>> {
        logService.info("findAll() - start")
        val result = FindAllSubjects(serviceFactory).execute()
        logService.info("findAll() - end")
        return ResponseEntity(result, HttpStatus.OK)
    }

    @PostMapping(UrlUtils.save)
    override fun save(@RequestBody json: String?): ResponseEntity<List<SubjectDto>> {
        logService.info("save(json: ${logService.formatJson(json)}) - start")
        val result = SaveSubject(serviceFactory, json).execute()
        logService.info("save(json:${logService.formatJson(json)}) - end")
        return ResponseEntity(result, HttpStatus.OK)
    }
}