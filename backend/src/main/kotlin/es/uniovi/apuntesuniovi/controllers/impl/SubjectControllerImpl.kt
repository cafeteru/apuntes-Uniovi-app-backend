package es.uniovi.apuntesuniovi.controllers.impl

import es.uniovi.apuntesuniovi.controllers.SubjectController
import es.uniovi.apuntesuniovi.controllers.impl.subjects.FindAllSubjects
import es.uniovi.apuntesuniovi.controllers.impl.subjects.SaveSubject
import es.uniovi.apuntesuniovi.log.LogService
import es.uniovi.apuntesuniovi.servicies.ServiceFactory
import es.uniovi.apuntesuniovi.servicies.dtos.entities.SubjectDto
import es.uniovi.apuntesuniovi.servicies.messages.LoadMessages
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.security.Principal

@RestController
@RequestMapping("/subjects")
class SubjectControllerImpl @Autowired constructor(
        private val serviceFactory: ServiceFactory,
        private val loadMessages: LoadMessages
) : SubjectController {
    private val logService: LogService = LogService(this)

    @GetMapping("")
    override fun findAll(principal: Principal?): ResponseEntity<List<SubjectDto>> {
        logService.info(principal?.name + " " + loadMessages.getString("subject.find.all"))
        return ResponseEntity<List<SubjectDto>>(FindAllSubjects(serviceFactory).execute(), HttpStatus.OK)
    }

    @PostMapping("/save")
    override fun save(principal: Principal?, @RequestBody json: String?): ResponseEntity<List<SubjectDto>> {
        logService.info(principal?.name + " " + loadMessages.getString("subject.save"))
        return ResponseEntity<List<SubjectDto>>(SaveSubject(serviceFactory, loadMessages, json).execute(), HttpStatus.OK)
    }
}