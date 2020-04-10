package es.uniovi.apuntesuniovi.controllers.impl.subjects

import com.google.gson.Gson
import es.uniovi.apuntesuniovi.infrastructure.Command
import es.uniovi.apuntesuniovi.servicies.ServiceFactory
import es.uniovi.apuntesuniovi.servicies.dtos.entities.SubjectDto

class SaveSubject(
        private val serviceFactory: ServiceFactory,
        private val json: String?
) : Command<List<SubjectDto>> {

    override fun execute(): List<SubjectDto> {
        if (json == null || json.isEmpty()) {
            throw IllegalArgumentException("Asignatura no válida")
        }
        val subject: SubjectDto = Gson().fromJson(json, SubjectDto::class.java)
        return serviceFactory.getSubjects().save(subject)
    }

}