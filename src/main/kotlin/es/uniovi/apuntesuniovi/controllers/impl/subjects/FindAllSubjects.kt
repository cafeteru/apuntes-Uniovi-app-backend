package es.uniovi.apuntesuniovi.controllers.impl.subjects

import es.uniovi.apuntesuniovi.infrastructure.Command
import es.uniovi.apuntesuniovi.servicies.ServiceFactory
import es.uniovi.apuntesuniovi.servicies.dtos.entities.SubjectDto

class FindAllSubjects(
        private val serviceFactory: ServiceFactory
) : Command<List<SubjectDto>> {

    override fun execute(): List<SubjectDto> {
        return serviceFactory.getSubjects().findAll()
    }

}