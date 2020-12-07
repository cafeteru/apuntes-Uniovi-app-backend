package es.uniovi.apuntesuniovi.controllers.commands.courses

import es.uniovi.apuntesuniovi.controllers.commands.AbstractFindAll
import es.uniovi.apuntesuniovi.services.CourseService
import es.uniovi.apuntesuniovi.services.dtos.entities.CourseDto

/**
 * Return all courses in controller layer
 */
class FindAllCourses(courseService: CourseService) : AbstractFindAll<CourseDto>(courseService)