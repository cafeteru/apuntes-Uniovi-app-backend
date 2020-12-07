package es.uniovi.apuntesuniovi.controllers

import es.uniovi.apuntesuniovi.controllers.commands.courses.CreateCourse
import es.uniovi.apuntesuniovi.controllers.commands.courses.FindAllCourses
import es.uniovi.apuntesuniovi.infrastructure.log.LogService
import es.uniovi.apuntesuniovi.services.CourseService
import es.uniovi.apuntesuniovi.services.dtos.entities.CourseDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

/**
 * Define courses endpoints
 */
@RestController
@RequestMapping("/courses")
class CourseController @Autowired constructor(
    private val courseService: CourseService
) {
    private val logService = LogService(this.javaClass)

    /**
     * Returns all registered courses in the system
     */
    @GetMapping("")
    fun findAll(): ResponseEntity<List<CourseDto>> {
        logService.info("findAll() - start")
        val result = FindAllCourses(courseService).execute()
        logService.info("findAll() - end")
        return ResponseEntity(result, HttpStatus.OK)
    }

    /**
     * Add a new course through a text string (JSON)
     */
    @PostMapping("/create")
    fun save(@RequestBody json: String): ResponseEntity<List<CourseDto>> {
        logService.info("save(json: String) - start")
        val result = CreateCourse(courseService, json).execute()
        logService.info("save(json: String) - end")
        return ResponseEntity(result, HttpStatus.OK)
    }
}