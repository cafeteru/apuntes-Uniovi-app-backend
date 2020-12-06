package es.uniovi.apuntesuniovi.servicies.commands.subjects

import es.uniovi.apuntesuniovi.infrastructure.Command
import es.uniovi.apuntesuniovi.infrastructure.log.LogService
import es.uniovi.apuntesuniovi.repositories.SubjectRepository
import es.uniovi.apuntesuniovi.servicies.dtos.entities.SubjectDto
import es.uniovi.apuntesuniovi.servicies.dtos.impl.SubjectDtoAssembler
import java.util.*

/**
 * Create a subject in service layer
 */
class CreateSubjectService(
    private val subjectRepository: SubjectRepository,
    private val subjectDtoAssembler: SubjectDtoAssembler,
    private val subjectDto: SubjectDto
) : Command<List<SubjectDto>> {
    private val logService = LogService(this.javaClass)

    override fun execute(): List<SubjectDto> {
        logService.info("execute() - start")
        val subject = subjectDtoAssembler.dtoToEntity(subjectDto)
        val result = subjectRepository.save(subject)
        val list = ArrayList<SubjectDto>()
        list.add(subjectDtoAssembler.entityToDto(result))
        logService.info("execute() - end")
        return list
    }
}