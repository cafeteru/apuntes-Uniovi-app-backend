package es.uniovi.apuntesuniovi.services.commands

import es.uniovi.apuntesuniovi.infrastructure.AbstractCommand
import org.springframework.data.repository.PagingAndSortingRepository

/**
 * Create a entity in service layer
 */
abstract class BaseCreateService<Entity>(
  private val repository: PagingAndSortingRepository<Entity, Long>,
  private val entity: Entity,
) : AbstractCommand<Entity>() {
  override fun execute(): Entity {
    logService.info("execute() - start")
    val result = repository.save(entity)
    logService.info("execute() - end")
    return result
  }
}