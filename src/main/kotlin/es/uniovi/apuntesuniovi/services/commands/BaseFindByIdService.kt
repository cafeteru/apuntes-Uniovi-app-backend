package es.uniovi.apuntesuniovi.services.commands

import es.uniovi.apuntesuniovi.infrastructure.AbstractCommand
import es.uniovi.apuntesuniovi.validators.impl.ValidatorId
import org.springframework.data.repository.PagingAndSortingRepository

/**
 * Find entity by id
 */
abstract class BaseFindByIdService<Entity>(
  private val repository: PagingAndSortingRepository<Entity, Long>,
  private val id: Long
) : AbstractCommand<Entity>() {
  override fun execute(): Entity {
    logService.info("execute() - start")
    if (ValidatorId(id).isValid()) {
      val optional = repository.findById(id)
      if (optional.isPresent) {
        logService.info("execute() - end")
        return optional.get()
      }
      logService.error("execute() - error")
      throw IllegalArgumentException(getMessageNotFound())
    }
    logService.error("execute() - error")
    throw IllegalArgumentException(getMessageInvalidId())
  }

  /**
   * Return message if not found entity
   */
  abstract fun getMessageNotFound(): String

  /**
   * Return message if id is invalid
   */
  abstract fun getMessageInvalidId(): String
}