package es.uniovi.apuntesuniovi.servicies.impl.subjects

import es.uniovi.apuntesuniovi.infrastructure.Command
import es.uniovi.apuntesuniovi.infrastructure.constants.ExceptionMessages
import es.uniovi.apuntesuniovi.log.LogService
import es.uniovi.apuntesuniovi.repositories.SubjectRepository
import es.uniovi.apuntesuniovi.servicies.dtos.entities.SubjectDto
import es.uniovi.apuntesuniovi.servicies.dtos.impl.SubjectDtoAssembler

class SaveSubjectService(
        private val subjectRepository: SubjectRepository,
        private val subjectDtoAssembler: SubjectDtoAssembler,
        private val subjectDto: SubjectDto
) : Command<List<SubjectDto>> {
    private val logService = LogService(this.javaClass)

    override fun execute(): List<SubjectDto> {
        logService.info("execute() - start")
        check()
        val list = ArrayList<SubjectDto>()
        val subject = subjectDtoAssembler.dtoToEntity(subjectDto)
        val result = subjectRepository.save(subject)
        list.add(subjectDtoAssembler.entityToDto(result))
        logService.info("execute() - end")
        return list
    }

    private fun check() {
        logService.info("check() - start")
        val optional = subjectRepository.findByNameAndCourse(subjectDto.name, subjectDto.course)
        if (optional.isPresent) {
            logService.error("check() - error")
            throw IllegalArgumentException(ExceptionMessages.ALREADY_REGISTERED_SUBJECT)
        }
        logService.info("check() - end")
    }
}