package es.uniovi.apuntesuniovi.controllers.impl

import es.uniovi.apuntesuniovi.controllers.StudentController
import es.uniovi.apuntesuniovi.entities.Student
import es.uniovi.apuntesuniovi.repositories.RepositoryFactory
import es.uniovi.apuntesuniovi.servicies.messages.LoadMessages
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@RestController
@RequestMapping("/students")
class StudentControllerImpl @Autowired constructor(
        private val serviceFactory: RepositoryFactory,
        private val loadMessages: LoadMessages
) : StudentController {

    @GetMapping("")
    override fun findAll(principal: Principal?): ResponseEntity<List<Student>> {
        return ResponseEntity<List<Student>>(serviceFactory.getStudents().findAll(), HttpStatus.OK)
    }
}