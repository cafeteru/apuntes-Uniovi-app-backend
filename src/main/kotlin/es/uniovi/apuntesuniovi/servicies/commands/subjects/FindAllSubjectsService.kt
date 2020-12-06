package es.uniovi.apuntesuniovi.servicies.commands.subjects

import es.uniovi.apuntesuniovi.infrastructure.Command
import es.uniovi.apuntesuniovi.infrastructure.log.LogService
import es.uniovi.apuntesuniovi.repositories.SubjectRepository
import es.uniovi.apuntesuniovi.servicies.dtos.entities.SubjectDto
import es.uniovi.apuntesuniovi.servicies.dtos.impl.SubjectDtoAssembler

/**
 * Return all subjects in service layer
 */
class FindAllSubjectsService(
    private val subjectRepository: SubjectRepository,
    private val subjectDtoAssembler: SubjectDtoAssembler
) : Command<List<SubjectDto>> {
    private val logService = LogService(this.javaClass)

    override fun execute(): List<SubjectDto> {
        logService.info("execute() - start")
        val list = subjectRepository.findAll()
        val result = subjectDtoAssembler.listToDto(list)
        logService.info("execute() - end")
        return result
    }
}