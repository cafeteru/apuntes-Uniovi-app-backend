package es.uniovi.apuntesuniovi.controllers.commands.subjects

import com.google.gson.Gson
import es.uniovi.apuntesuniovi.controllers.commands.BaseCreate
import es.uniovi.apuntesuniovi.infrastructure.messages.SubjectMessages
import es.uniovi.apuntesuniovi.infrastructure.messages.TeachSubjectMessages
import es.uniovi.apuntesuniovi.models.Subject
import es.uniovi.apuntesuniovi.services.SubjectService
import es.uniovi.apuntesuniovi.services.dtos.entities.SubjectDto

/**
 * Save a subject in controller layer
 */
class CreateSubject(
    subjectService: SubjectService,
    json: String
) : BaseCreate<Subject, SubjectDto>(subjectService, json) {

    override fun getEntityFromJson(json: String): SubjectDto {
        return Gson().fromJson(json, SubjectDto::class.java)
    }

    override fun getMessageInvalidJson(): String {
        return SubjectMessages.INVALID_JSON
    }
}