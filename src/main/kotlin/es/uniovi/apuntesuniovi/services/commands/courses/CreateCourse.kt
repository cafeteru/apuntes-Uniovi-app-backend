package es.uniovi.apuntesuniovi.services.commands.courses

import es.uniovi.apuntesuniovi.models.Course
import es.uniovi.apuntesuniovi.repositories.CourseRepository
import es.uniovi.apuntesuniovi.services.commands.BaseCreateService

/**
 * Create a course in service layer
 */
class CreateCourse(courseRepository: CourseRepository, course: Course) :
  BaseCreateService<Course>(courseRepository, course)