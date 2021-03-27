package es.uniovi.apuntesuniovi.dtos.assemblers

import es.uniovi.apuntesuniovi.infrastructure.messages.UnitSubjectMessages
import es.uniovi.apuntesuniovi.models.UnitSubject
import es.uniovi.apuntesuniovi.repositories.SubjectRepository
import es.uniovi.apuntesuniovi.services.commands.subjects.FindSubjectById
import es.uniovi.apuntesuniovi.dtos.entities.UnitSubjectDto
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
    throw IllegalArgumentException(UnitSubjectMessages.NULL)
  }

  override fun dtoToEntity(dto: UnitSubjectDto?): UnitSubject {
    logService.info("dtoToEntity(dto: UnitSubjectDto) - start")
    dto?.let {
      val entity = UnitSubject()
      entity.id = it.id
      if (it.name == null) {
        logService.info("dtoToEntity(dto: UnitSubjectDto) - error: ${UnitSubjectMessages.NULL_NAME}")
        throw IllegalArgumentException(UnitSubjectMessages.NULL_NAME)
      } else {
        entity.name = it.name
      }
      if (it.position == null) {
        logService.info("dtoToEntity(dto: UnitSubjectDto) - error: ${UnitSubjectMessages.NULL_POSITION}")
        throw IllegalArgumentException(UnitSubjectMessages.NULL_POSITION)
      } else {
        entity.position = it.position
      }
      it.subjectId?.let { id ->
        entity.subject = FindSubjectById(subjectRepository, id).execute()
      } ?: run {
        logService.info("dtoToEntity(dto: UnitSubjectDto) - error: ${UnitSubjectMessages.NULL_SUBJECT}")
        throw IllegalArgumentException(UnitSubjectMessages.NULL_SUBJECT)
      }
      logService.info("dtoToEntity(dto: UnitSubjectDto) - end")
      return entity
    }
    throw IllegalArgumentException(UnitSubjectMessages.NULL)
  }
}