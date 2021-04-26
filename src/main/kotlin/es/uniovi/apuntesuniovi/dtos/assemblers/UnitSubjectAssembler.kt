package es.uniovi.apuntesuniovi.dtos.assemblers

import es.uniovi.apuntesuniovi.dtos.entities.UnitSubjectDto
import es.uniovi.apuntesuniovi.infrastructure.messages.UnitSubjectMessages
import es.uniovi.apuntesuniovi.models.UnitSubject
import es.uniovi.apuntesuniovi.repositories.SubjectRepository
import es.uniovi.apuntesuniovi.services.commands.subjects.FindSubjectById

/**
 * Define the entity and dto conversion methods of careers
 */
class UnitSubjectAssembler(
    private val subjectRepository: SubjectRepository
) : AbstractAssembler<UnitSubject, UnitSubjectDto>() {

    override fun entityToDto(entity: UnitSubject?): UnitSubjectDto {
        logService.info("entityToDto(entity: UnitSubject) - start")
        entity?.let {
            val dto = UnitSubjectDto(
                id = it.id,
                name = it.name,
                position = it.position,
                subjectId = it.subject?.id
            )
            logService.info("entityToDto(entity: UnitSubject) - end")
            return dto
        }
        throw IllegalArgumentException(UnitSubjectMessages.NULL)
    }

    override fun dtoToEntity(dto: UnitSubjectDto?): UnitSubject {
        logService.info("dtoToEntity(dto: UnitSubjectDto) - start")
        dto?.let {
            val entity = UnitSubject()
            entity.id = it.id
            entity.name = it.name ?: throw IllegalArgumentException(UnitSubjectMessages.NULL_NAME)
            val id = it.subjectId ?: throw IllegalArgumentException(UnitSubjectMessages.NULL_SUBJECT)
            entity.subject = FindSubjectById(subjectRepository, id).execute()
            entity.position = dto.position ?: 1
            logService.info("dtoToEntity(dto: UnitSubjectDto) - end")
            return entity
        }
        throw IllegalArgumentException(UnitSubjectMessages.NULL)
    }
}