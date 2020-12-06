package es.uniovi.apuntesuniovi.services.dtos.assemblers

import es.uniovi.apuntesuniovi.models.TeachSubjectRegistry
import es.uniovi.apuntesuniovi.services.dtos.entities.TeachSubjectRegistryDto

/**
 * Define the entity and dto conversion methods of teachSubjectRegistry
 */
class TeachSubjectRegistryAssembler : AbstractDtoAssembler<TeachSubjectRegistry, TeachSubjectRegistryDto>() {
    override fun entityToDto(entity: TeachSubjectRegistry?): TeachSubjectRegistryDto {
        logService.info("entityToDto(entity: ${entity}) - start")
        entity?.let {
            it.teachSubject.id?.let { id ->
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
            entity.id = dto.id
            // TODO FIND teachSubject
            entity.initDay = dto.initDay
            entity.finishDay = dto.finishDay
            return entity
        }
        logService.error("dtoToEntity(dto: ${dto}) - error")
        throw IllegalArgumentException()
    }
}