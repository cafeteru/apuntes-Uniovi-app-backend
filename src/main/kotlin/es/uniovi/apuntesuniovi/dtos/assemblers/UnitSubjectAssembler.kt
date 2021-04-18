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
                subjectId = it.subject?.id
            )
            logService.info("entityToDto(entity: UnitSubject) - end")
            return dto
        }
        logService.error("entityToDto(entity: UnitSubject) - error: ${UnitSubjectMessages.NULL}")
        throw IllegalArgumentException(UnitSubjectMessages.NULL)
    }

    override fun dtoToEntity(dto: UnitSubjectDto?): UnitSubject {
        logService.info("dtoToEntity(dto: UnitSubjectDto) - start")
        dto?.let {
            val entity = UnitSubject()
            entity.id = it.id
            if (it.name == null) {
                logService.error("dtoToEntity(dto: UnitSubjectDto) - error: ${UnitSubjectMessages.NULL_NAME}")
                throw IllegalArgumentException(UnitSubjectMessages.NULL_NAME)
            } else {
                entity.name = it.name
            }
            it.subjectId?.let { id ->
                entity.subject = FindSubjectById(subjectRepository, id).execute()
            } ?: run {
                logService.error("dtoToEntity(dto: UnitSubjectDto) - error: ${UnitSubjectMessages.NULL_SUBJECT}")
                throw IllegalArgumentException(UnitSubjectMessages.NULL_SUBJECT)
            }
            logService.info("dtoToEntity(dto: UnitSubjectDto) - end")
            return entity
        }
        logService.error("dtoToEntity(dto: UnitSubjectDto) - error: ${UnitSubjectMessages.NULL}")
        throw IllegalArgumentException(UnitSubjectMessages.NULL)
    }
}