package es.uniovi.apuntesuniovi.controllers.commands.courses

import es.uniovi.apuntesuniovi.controllers.commands.BaseFindAll
import es.uniovi.apuntesuniovi.models.Course
import es.uniovi.apuntesuniovi.services.CourseService
import es.uniovi.apuntesuniovi.services.dtos.entities.CourseDto
import org.springframework.data.domain.Pageable

/**
 * Return all courses in controller layer
 */
class FindAllCourses(
  courseService: CourseService,
  pageable: Pageable
) : BaseFindAll<Course, CourseDto>(courseService, pageable)