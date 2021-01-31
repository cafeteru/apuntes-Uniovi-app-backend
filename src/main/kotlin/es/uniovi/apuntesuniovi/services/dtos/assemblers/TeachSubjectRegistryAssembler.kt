package es.uniovi.apuntesuniovi.services.dtos.assemblers

import es.uniovi.apuntesuniovi.infrastructure.messages.TeachSubjectRegistryMessages
import es.uniovi.apuntesuniovi.models.TeachSubjectRegistry
import es.uniovi.apuntesuniovi.repositories.TeachSubjectRepository
import es.uniovi.apuntesuniovi.services.commands.teachSubjects.FindTeachSubjectByIdService
import es.uniovi.apuntesuniovi.services.dtos.entities.TeachSubjectRegistryDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Define the entity and dto conversion methods of teachSubjectRegistry
 */
@Service
class TeachSubjectRegistryAssembler @Autowired constructor(
  private val teachSubjectRepository: TeachSubjectRepository
) : AbstractAssembler<TeachSubjectRegistry, TeachSubjectRegistryDto>() {
  override fun entityToDto(entity: TeachSubjectRegistry?): TeachSubjectRegistryDto {
    logService.info("entityToDto(entity: TeachSubjectRegistry) - start")
    entity?.let {
      it.teachSubject.id?.let { id ->
        val result = TeachSubjectRegistryDto(
          id = it.id,
          teachSubjectId = id,
          initDay = it.initDay,
          finishDay = it.finishDay
        )
        logService.info("entityToDto(entity: TeachSubjectRegistry) - end")
        return result
      }
    }
    logService.error("entityToDto(entity: TeachSubjectRegistry) - error: ${TeachSubjectRegistryMessages.NULL}")
    throw IllegalArgumentException(TeachSubjectRegistryMessages.NULL)
  }

  override fun dtoToEntity(dto: TeachSubjectRegistryDto?): TeachSubjectRegistry {
    logService.info("dtoToEntity(entity: TeachSubjectRegistryDto) - start")
    dto?.let {
      val entity = TeachSubjectRegistry()
      entity.id = it.id
      it.teachSubjectId?.let { id ->
        entity.teachSubject = FindTeachSubjectByIdService(teachSubjectRepository, id).execute()
      }
      entity.initDay = it.initDay
      entity.finishDay = it.finishDay
      return entity
    }
    logService.error("dtoToEntity(dto: TeachSubjectRegistryDto) - error: ${TeachSubjectRegistryMessages.NULL}")
    throw IllegalArgumentException(TeachSubjectRegistryMessages.NULL)
  }
}