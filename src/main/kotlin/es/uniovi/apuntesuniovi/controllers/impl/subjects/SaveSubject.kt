package es.uniovi.apuntesuniovi.controllers.impl.subjects

import com.google.gson.Gson
import es.uniovi.apuntesuniovi.infrastructure.Command
import es.uniovi.apuntesuniovi.log.LogService
import es.uniovi.apuntesuniovi.servicies.ServiceFactory
import es.uniovi.apuntesuniovi.servicies.dtos.entities.SubjectDto

class SaveSubject(
        private val serviceFactory: ServiceFactory,
        private val json: String?
) : Command<List<SubjectDto>> {
    private val logService = LogService(this.javaClass)

    override fun execute(): List<SubjectDto> {
        logService.info("execute() - start")
        if (json.isNullOrEmpty()) {
            throw IllegalArgumentException("")
        }
        val subject: SubjectDto = Gson().fromJson(json, SubjectDto::class.java)
        val result = serviceFactory.getSubjects().save(subject)
        logService.info("execute() - end")
        return result
    }

}