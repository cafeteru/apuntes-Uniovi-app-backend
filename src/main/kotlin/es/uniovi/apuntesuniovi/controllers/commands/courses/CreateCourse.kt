package es.uniovi.apuntesuniovi.controllers.commands.courses

import com.google.gson.Gson
import es.uniovi.apuntesuniovi.controllers.commands.BaseCreate
import es.uniovi.apuntesuniovi.infrastructure.messages.CourseMessages
import es.uniovi.apuntesuniovi.models.Course
import es.uniovi.apuntesuniovi.services.CourseService
import es.uniovi.apuntesuniovi.services.dtos.entities.CourseDto

/**
 * Create a center in controller layer
 */
class CreateCourse(
  courseService: CourseService,
  json: String
) : BaseCreate<Course, CourseDto>(courseService, json) {

  override fun getEntityFromJson(json: String): CourseDto {
    return Gson().fromJson(json, CourseDto::class.java)
  }

  override fun getMessageInvalidJson(): String {
    return CourseMessages.INVALID_JSON
  }
}