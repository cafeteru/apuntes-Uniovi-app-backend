package es.uniovi.apuntesuniovi.controllers.commands.unitSubjects

import com.google.gson.Gson
import es.uniovi.apuntesuniovi.controllers.commands.BaseCreate
import es.uniovi.apuntesuniovi.infrastructure.messages.UnitSubjectMessages
import es.uniovi.apuntesuniovi.models.UnitSubject
import es.uniovi.apuntesuniovi.services.UnitSubjectService
import es.uniovi.apuntesuniovi.services.dtos.entities.UnitSubjectDto

/**
 * Save a UnitSubject in controller layer
 */
class CreateUnitSubject(
    unitSubjectService: UnitSubjectService,
    json: String
) : BaseCreate<UnitSubject, UnitSubjectDto>(unitSubjectService, json) {

    override fun getEntityFromJson(json: String): UnitSubjectDto {
        return Gson().fromJson(json, UnitSubjectDto::class.java)
    }

    override fun getMessageInvalidJson(): String {
        return UnitSubjectMessages.INVALID_JSON
    }
}