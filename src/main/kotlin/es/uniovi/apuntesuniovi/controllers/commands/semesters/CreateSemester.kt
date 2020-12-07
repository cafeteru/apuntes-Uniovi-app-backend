package es.uniovi.apuntesuniovi.controllers.commands.semesters

import com.google.gson.Gson
import es.uniovi.apuntesuniovi.controllers.commands.BaseCreate
import es.uniovi.apuntesuniovi.services.SemesterService
import es.uniovi.apuntesuniovi.services.dtos.entities.SemesterDto

/**
 * Create a semester in controller layer
 */
class CreateSemester(
    semesterService: SemesterService,
    json: String
) : BaseCreate<SemesterDto>(semesterService, json) {

    override fun getEntityFromJson(json: String): SemesterDto {
        return Gson().fromJson(json, SemesterDto::class.java)
    }
}