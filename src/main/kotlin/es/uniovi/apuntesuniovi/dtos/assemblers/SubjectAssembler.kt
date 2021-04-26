package es.uniovi.apuntesuniovi.dtos.assemblers

import es.uniovi.apuntesuniovi.dtos.entities.SubjectDto
import es.uniovi.apuntesuniovi.infrastructure.messages.SubjectMessages
import es.uniovi.apuntesuniovi.models.Subject

/**
 * Define the entity and dto conversion methods of subjects
 */
class SubjectAssembler : AbstractAssembler<Subject, SubjectDto>() {

    override fun entityToDto(entity: Subject?): SubjectDto {
        logService.info("entityToDto(entity: Subject) - start")
        entity?.let {
            val result = SubjectDto(
                id = it.id,
                name = it.name,
                subjectType = it.subjectType.toString(),
                active = it.active
            )
            logService.info("entityToDto(entity: Subject) - end")
            return result
        }
        throw IllegalArgumentException(SubjectMessages.NULL)
    }

    override fun dtoToEntity(dto: SubjectDto?): Subject {
        logService.info("dtoToEntity(dto: SubjectDto) - start")
        dto?.let {
            val result = Subject()
            result.id = it.id
            result.name = it.name
            result.setSubjectType(it.subjectType)
            result.active = it.active.toString().toBoolean()
            logService.info("dtoToEntity(dto: SubjectDto) - end")
            return result
        }
        throw IllegalArgumentException(SubjectMessages.NULL)
    }
}