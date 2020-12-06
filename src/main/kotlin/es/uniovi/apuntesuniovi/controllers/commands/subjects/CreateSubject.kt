package es.uniovi.apuntesuniovi.controllers.commands.subjects

import com.google.gson.Gson
import es.uniovi.apuntesuniovi.infrastructure.AbstractCommand
import es.uniovi.apuntesuniovi.servicies.SubjectService
import es.uniovi.apuntesuniovi.servicies.dtos.entities.SubjectDto

/**
 * Save a subject in controller layer
 */
class CreateSubject(
    private val subjectService: SubjectService,
    private val json: String
) : AbstractCommand<List<SubjectDto>>() {
    override fun execute(): List<SubjectDto> {
        logService.info("execute() - start")
        val subject: SubjectDto = Gson().fromJson(json, SubjectDto::class.java)
        val result = subjectService.create(subject)
        logService.info("execute() - end")
        return result
    }
}