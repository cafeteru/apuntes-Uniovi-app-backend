package es.uniovi.apuntesuniovi.services.commands.courses

import es.uniovi.apuntesuniovi.infrastructure.messages.CourseMessages
import es.uniovi.apuntesuniovi.models.Course
import es.uniovi.apuntesuniovi.repositories.CourseRepository
import es.uniovi.apuntesuniovi.services.commands.BaseFindByIdService

/**
 * Return course by id in service layer
 */
class FindCourseByIdService(
  courseRepository: CourseRepository,
  id: Long
) : BaseFindByIdService<Course>(courseRepository, id) {
  override fun getMessageNotFound(): String {
    return CourseMessages.NOT_FOUND
  }

  override fun getMessageInvalidId(): String {
    return CourseMessages.INVALID_ID
  }
}