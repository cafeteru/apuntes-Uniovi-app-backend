package es.uniovi.apuntesuniovi.controllers.impl.subjects

import es.uniovi.apuntesuniovi.infrastructure.Command
import es.uniovi.apuntesuniovi.log.LogService
import es.uniovi.apuntesuniovi.servicies.ServiceFactory
import es.uniovi.apuntesuniovi.servicies.dtos.entities.SubjectDto

class FindAllSubjects(
        private val serviceFactory: ServiceFactory
) : Command<List<SubjectDto>> {
    private val logService = LogService(this.javaClass)

    override fun execute(): List<SubjectDto> {
        logService.info("execute() - start")
        val result = serviceFactory.getSubjects().findAll()
        logService.info("execute() - end")
        return result
    }

}