package es.uniovi.apuntesuniovi.controllers.commands.subjects

import com.google.gson.Gson
import es.uniovi.apuntesuniovi.infrastructure.Command
import es.uniovi.apuntesuniovi.log.LogService
import es.uniovi.apuntesuniovi.servicies.SubjectService
import es.uniovi.apuntesuniovi.servicies.dtos.entities.SubjectDto

class SaveSubject(
        private val subjectService: SubjectService,
        private val json: String
) : Command<List<SubjectDto>> {
    private val logService = LogService(this.javaClass)

    override fun execute(): List<SubjectDto> {
        logService.info("execute() - start")
        val subject: SubjectDto = Gson().fromJson(json, SubjectDto::class.java)
        val result = subjectService.create(subject)
        logService.info("execute() - end")
        return result
    }

}