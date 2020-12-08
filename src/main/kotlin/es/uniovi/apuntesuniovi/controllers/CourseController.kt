package es.uniovi.apuntesuniovi.controllers

import es.uniovi.apuntesuniovi.controllers.commands.courses.CreateCourse
import es.uniovi.apuntesuniovi.controllers.commands.courses.FindAllCourses
import es.uniovi.apuntesuniovi.models.Course
import es.uniovi.apuntesuniovi.services.BaseService
import es.uniovi.apuntesuniovi.services.CourseService
import es.uniovi.apuntesuniovi.services.dtos.entities.CourseDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Define courses endpoints
 */
@RestController
@RequestMapping("/courses")
class CourseController @Autowired constructor(
    private val courseService: CourseService
) : BaseController<Course, CourseDto>(courseService) {

    override fun create(baseService: BaseService<Course, CourseDto>, json: String): List<CourseDto> {
        return CreateCourse(courseService, json).execute()
    }

    override fun findAll(baseService: BaseService<Course, CourseDto>, pageable: Pageable): Page<CourseDto> {
        return FindAllCourses(courseService, pageable).execute()
    }
}