package es.uniovi.apuntesuniovi.servicies.dtos.impl

import es.uniovi.apuntesuniovi.entities.TeachSubject
import es.uniovi.apuntesuniovi.servicies.dtos.AbstractDtoAssembler
import es.uniovi.apuntesuniovi.servicies.dtos.entities.TeachSubjectDto
import org.springframework.stereotype.Service

@Service
class TeachSubjectDtoAssembler : AbstractDtoAssembler<TeachSubject, TeachSubjectDto>() {
    override fun entityToDto(entity: TeachSubject?): TeachSubjectDto {
        logService.info("entityToDto(entity: ${entity}) - start")
        entity?.let {
            val result = TeachSubjectDto(
                    id = it.id,
                    isCoordinator = it.isCoordinator,
                    subjectId = it.subject.id,
                    teacherId = it.teacher.id)
            logService.info("entityToDto(entity: ${entity}) - end")
            return result
        }
        logService.error("entityToDto(entity: ${entity}) - error")
        throw IllegalArgumentException()
    }

    override fun dtoToEntity(dto: TeachSubjectDto?): TeachSubject {
        logService.info("dtoToEntity(dto: ${dto}) - start")
        dto?.let {
            val result = TeachSubject()
            result.id = it.id
            result.isCoordinator = it.isCoordinator
            // TODO result.subject = subject
            // TODO result.teacher = teacher
            logService.info("dtoToEntity(dto: ${dto}) - end")
            return result
        }
        logService.error("dtoToEntity(dto: ${dto}) - error")
        throw IllegalArgumentException()
    }
}