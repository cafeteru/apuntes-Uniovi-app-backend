package es.uniovi.apuntesuniovi.controllers.impl.subjects

import com.google.gson.Gson
import es.uniovi.apuntesuniovi.infrastructure.Command
import es.uniovi.apuntesuniovi.servicies.ServiceFactory
import es.uniovi.apuntesuniovi.servicies.dtos.entities.SubjectDto
import es.uniovi.apuntesuniovi.servicies.messages.LoadMessages

class SaveSubject(
        private val serviceFactory: ServiceFactory,
        private val loadMessages: LoadMessages,
        private val json: String?
) : Command<List<SubjectDto>> {

    override fun execute(): List<SubjectDto> {
        if (json == null || json.isEmpty()) {
            throw IllegalArgumentException(loadMessages.getString("error.save.subject"))
        }
        val subject: SubjectDto = Gson().fromJson(json, SubjectDto::class.java)
        return serviceFactory.getSubjects().save(subject)
    }

}