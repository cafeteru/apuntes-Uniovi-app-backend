package es.uniovi.apuntesuniovi.services.commands.courses

import es.uniovi.apuntesuniovi.models.Course
import es.uniovi.apuntesuniovi.repositories.CourseRepository
import es.uniovi.apuntesuniovi.services.commands.BaseFindAllService
import org.springframework.data.domain.Pageable

/**
 * Return all courses in service layer
 */
class FindAllCoursesService(
  courseRepository: CourseRepository,
  pageable: Pageable
) : BaseFindAllService<Course>(courseRepository, pageable)