package es.uniovi.apuntesuniovi.dtos.assemblers

import es.uniovi.apuntesuniovi.infrastructure.log.LogService

/**
 * Abstract class to define the entity and dto conversion methods
 */
abstract class AbstractAssembler<Entity, Dto> {
  protected val logService = LogService(this.javaClass)

  /**
   * Convert an entity into to dto
   *
   * @param entity Entity to convert
   */
  abstract fun entityToDto(entity: Entity?): Dto

  /**
   * Convert an dto into to entity
   *
   * @param dto Dto to convert
   */
  abstract fun dtoToEntity(dto: Dto?): Entity
}