package es.uniovi.apuntesuniovi.controllers.commands.teachSubjects

import com.google.gson.Gson
import es.uniovi.apuntesuniovi.controllers.commands.BaseCreate
import es.uniovi.apuntesuniovi.infrastructure.messages.TeachSubjectMessages
import es.uniovi.apuntesuniovi.models.TeachSubject
import es.uniovi.apuntesuniovi.services.TeachSubjectService
import es.uniovi.apuntesuniovi.services.dtos.entities.TeachSubjectDto

/**
 * Save a subject in controller layer
 */
class CreateTeachSubject(
  teachSubjectService: TeachSubjectService,
  json: String
) : BaseCreate<TeachSubject, TeachSubjectDto>(teachSubjectService, json) {

  override fun getEntityFromJson(json: String): TeachSubjectDto {
    return Gson().fromJson(json, TeachSubjectDto::class.java)
  }

  override fun getMessageInvalidJson(): String {
    return TeachSubjectMessages.INVALID_JSON
  }
}