package es.uniovi.apuntesuniovi.services.commands

import es.uniovi.apuntesuniovi.infrastructure.AbstractCommand
import es.uniovi.apuntesuniovi.infrastructure.messages.UserMessages
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
    checkData()
    entity?.let {
      val result = repository.save(entity)
      logService.info("execute() - end")
      return result
    }
    throw IllegalArgumentException(UserMessages.NULL)
  }

  /**
   * Check the data
   */
  abstract fun checkData();
}