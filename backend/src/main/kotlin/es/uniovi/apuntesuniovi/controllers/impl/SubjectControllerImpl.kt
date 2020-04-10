package es.uniovi.apuntesuniovi.controllers.impl

import es.uniovi.apuntesuniovi.controllers.SubjectController
import es.uniovi.apuntesuniovi.entities.Subject
import es.uniovi.apuntesuniovi.servicies.ServiceFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@RestController
@RequestMapping("/subjects")
class SubjectControllerImpl @Autowired constructor(
        private val serviceFactory: ServiceFactory
) : SubjectController {

    @GetMapping("")
    override fun findAll(principal: Principal?): ResponseEntity<List<Subject>> {
        val list: List<Subject> = serviceFactory.getSubjects().findAll()
        return ResponseEntity<List<Subject>>(list, HttpStatus.OK)
    }
}