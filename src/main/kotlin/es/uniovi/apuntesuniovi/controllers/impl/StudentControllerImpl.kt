package es.uniovi.apuntesuniovi.controllers.impl

import es.uniovi.apuntesuniovi.controllers.StudentController
import es.uniovi.apuntesuniovi.entities.Student
import es.uniovi.apuntesuniovi.infrastructure.constants.UrlUtils
import es.uniovi.apuntesuniovi.log.LogService
import es.uniovi.apuntesuniovi.repositories.RepositoryFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(UrlUtils.students)
class StudentControllerImpl @Autowired constructor(
        private val serviceFactory: RepositoryFactory
) : StudentController {
    private val logService = LogService(this.javaClass)

    @GetMapping(UrlUtils.findAll)
    override fun findAll(): ResponseEntity<List<Student>> {
        logService.info("findAll() - start")
        val result = serviceFactory.getStudents().findAll()
        logService.info("findAll() - end")
        return ResponseEntity(result, HttpStatus.OK)
    }
}