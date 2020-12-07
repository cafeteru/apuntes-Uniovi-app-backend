package es.uniovi.apuntesuniovi.services.commands.courses

import es.uniovi.apuntesuniovi.models.Course
import es.uniovi.apuntesuniovi.repositories.CourseRepository
import es.uniovi.apuntesuniovi.services.commands.BaseFindAllService

/**
 * Return all courses in service layer
 */
class FindAllCoursesService(courseRepository: CourseRepository) : BaseFindAllService<Course>(courseRepository)