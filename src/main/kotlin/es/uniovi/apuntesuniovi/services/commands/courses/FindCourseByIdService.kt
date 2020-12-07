package es.uniovi.apuntesuniovi.services.commands.courses

import es.uniovi.apuntesuniovi.models.Course
import es.uniovi.apuntesuniovi.repositories.CourseRepository
import es.uniovi.apuntesuniovi.services.commands.BaseFindByIdService

/**
 * Return course by id in service layer
 */
class FindCourseByIdService(
    courseRepository: CourseRepository,
    id: Long
) : BaseFindByIdService<Course>(courseRepository, id)