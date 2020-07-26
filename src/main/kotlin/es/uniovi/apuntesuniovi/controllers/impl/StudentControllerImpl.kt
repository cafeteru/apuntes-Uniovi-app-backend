package es.uniovi.apuntesuniovi.controllers.impl

import es.uniovi.apuntesuniovi.controllers.StudentController
import es.uniovi.apuntesuniovi.entities.Student
import es.uniovi.apuntesuniovi.infrastructure.GlobalConstants
import es.uniovi.apuntesuniovi.log.LogService
import es.uniovi.apuntesuniovi.repositories.RepositoryFactory
import es.uniovi.apuntesuniovi.servicies.messages.LoadMessages
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@Slf4j
@RestController
@RequestMapping(GlobalConstants.students)
class StudentControllerImpl @Autowired constructor(
        private val serviceFactory: RepositoryFactory,
        private val loadMessages: LoadMessages
) : StudentController {
    private val logService: LogService = LogService(this.javaClass)

    @GetMapping(GlobalConstants.findAll)
    override fun findAll(principal: Principal?): ResponseEntity<List<Student>> {
        logService.info(principal?.name + " " + loadMessages.getString("subject.find.all"))
        return ResponseEntity(serviceFactory.getStudents().findAll(), HttpStatus.OK)
    }
}