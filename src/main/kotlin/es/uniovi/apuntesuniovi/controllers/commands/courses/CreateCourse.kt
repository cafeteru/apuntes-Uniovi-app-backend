package es.uniovi.apuntesuniovi.controllers.commands.courses

import com.google.gson.Gson
import es.uniovi.apuntesuniovi.controllers.commands.AbstractCreate
import es.uniovi.apuntesuniovi.services.CourseService
import es.uniovi.apuntesuniovi.services.dtos.entities.CourseDto

/**
 * Create a center in controller layer
 */
class CreateCourse(
    courseService: CourseService,
    json: String
) : AbstractCreate<CourseDto>(courseService, json) {

    override fun getEntityFromJson(json: String): CourseDto {
        logService.info("getEntityFromJson(json: String) - start")
        val dto = Gson().fromJson(json, CourseDto::class.java)
        logService.info("getEntityFromJson(json: String) - end")
        return dto
    }
}