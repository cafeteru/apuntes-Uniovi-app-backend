package es.uniovi.apuntesuniovi.services.dtos.assemblers

import es.uniovi.apuntesuniovi.infrastructure.messages.UnitSubjectMessages
import es.uniovi.apuntesuniovi.models.UnitSubject
import es.uniovi.apuntesuniovi.repositories.SubjectRepository
import es.uniovi.apuntesuniovi.services.commands.subjects.FindSubjectByIdService
import es.uniovi.apuntesuniovi.services.dtos.entities.UnitSubjectDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Define the entity and dto conversion methods of careers
 */
@Service
class UnitSubjectAssembler @Autowired constructor(
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
        logService.error("entityToDto(entity: UnitSubject) - error")
        throw IllegalArgumentException(UnitSubjectMessages.NULL)
    }

    override fun dtoToEntity(dto: UnitSubjectDto?): UnitSubject {
        logService.info("dtoToEntity(dto: UnitSubjectDto) - start")
        dto?.let {
            val entity = UnitSubject()
            entity.id = it.id
            entity.position = it.position
            entity.name = it.name
            it.subjectId?.let { id ->
                entity.subject = FindSubjectByIdService(subjectRepository, id).execute()
            }
            logService.info("dtoToEntity(dto: UnitSubjectDto) - end")
            return entity
        }
        logService.info("dtoToEntity(dto: UnitSubjectDto) - error")
        throw IllegalArgumentException(UnitSubjectMessages.NULL)
    }
}