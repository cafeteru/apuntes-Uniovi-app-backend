package es.uniovi.apuntesuniovi.controllers.commands.subjects

import com.google.gson.Gson
import es.uniovi.apuntesuniovi.controllers.commands.AbstractCreate
import es.uniovi.apuntesuniovi.services.SubjectService
import es.uniovi.apuntesuniovi.services.dtos.entities.SubjectDto

/**
 * Save a subject in controller layer
 */
class CreateSubject(
    subjectService: SubjectService,
    json: String
) : AbstractCreate<SubjectDto>(subjectService, json) {

    override fun getEntityFromJson(json: String): SubjectDto {
        logService.info("getEntityFromJson(json: String) - start")
        val dto = Gson().fromJson(json, SubjectDto::class.java)
        logService.info("getEntityFromJson(json: String) - end")
        return dto
    }
}