package es.uniovi.apuntesuniovi.services.dtos.assemblers

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
        logService.info("entityToDto(entity: ${entity}) - start")
        entity?.let {
            it.teachSubject?.id?.let { id ->
                val result = TeachSubjectRegistryDto(
                    id = it.id,
                    teachSubjectId = id,
                    initDay = it.initDay,
                    finishDay = it.finishDay
                )
                logService.info("entityToDto(entity: ${entity}) - end")
                return result
            }
        }
        logService.error("entityToDto(entity: ${entity}) - error")
        throw IllegalArgumentException()
    }

    override fun dtoToEntity(dto: TeachSubjectRegistryDto?): TeachSubjectRegistry {
        logService.info("dtoToEntity(entity: ${dto}) - start")
        dto?.let {
            val entity = TeachSubjectRegistry()
            entity.id = it.id
            it.teachSubjectId?.let { id ->
                entity.teachSubject = FindTeachSubjectByIdService(teachSubjectRepository, id).execute()[0]
            }
            entity.initDay = it.initDay
            entity.finishDay = it.finishDay
            return entity
        }
        logService.error("dtoToEntity(dto: ${dto}) - error")
        throw IllegalArgumentException()
    }
}