package es.uniovi.apuntesuniovi.controllers

import es.uniovi.apuntesuniovi.controllers.commands.courses.CreateCourse
import es.uniovi.apuntesuniovi.controllers.commands.courses.FindAllCourses
import es.uniovi.apuntesuniovi.infrastructure.AbstractCommand
import es.uniovi.apuntesuniovi.services.BaseService
import es.uniovi.apuntesuniovi.services.CourseService
import es.uniovi.apuntesuniovi.services.dtos.entities.CourseDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Define courses endpoints
 */
@RestController
@RequestMapping("/courses")
class CourseController @Autowired constructor(
    private val courseService: CourseService
) : BaseController<CourseDto>(courseService) {

    override fun getCreateCommand(baseService: BaseService<CourseDto>, json: String): AbstractCommand<List<CourseDto>> {
        return CreateCourse(courseService, json)
    }

    override fun getFindAllCommand(baseService: BaseService<CourseDto>): AbstractCommand<List<CourseDto>> {
        return FindAllCourses(courseService)
    }
}