package es.uniovi.apuntesuniovi.services.dtos.assemblers

import es.uniovi.apuntesuniovi.infrastructure.messages.SubjectMessages
import es.uniovi.apuntesuniovi.models.Subject
import es.uniovi.apuntesuniovi.services.dtos.entities.SubjectDto
import org.springframework.stereotype.Service

/**
 * Define the entity and dto conversion methods of subjects
 */
@Service
class SubjectAssembler : AbstractAssembler<Subject, SubjectDto>() {
  override fun entityToDto(entity: Subject?): SubjectDto {
    logService.info("entityToDto(entity: Subject) - start")
    entity?.let {
      val result = SubjectDto(
        id = it.id,
        name = it.name,
        subjectType = it.subjectType.toString()
      )
      logService.info("entityToDto(entity: Subject) - end")
      return result
    }
    logService.error("entityToDto(entity: Subject) - error")
    throw IllegalArgumentException(SubjectMessages.NULL)
  }

  override fun dtoToEntity(dto: SubjectDto?): Subject {
    logService.info("dtoToEntity(dto: SubjectDto) - start")
    dto?.let {
      val result = Subject()
      result.id = it.id
      result.name = it.name
      result.setSubjectType(it.subjectType)
      logService.info("dtoToEntity(dto: SubjectDto) - end")
      return result
    }
    logService.error("dtoToEntity(dto: SubjectDto) - error")
    throw IllegalArgumentException(SubjectMessages.NULL)
  }
}