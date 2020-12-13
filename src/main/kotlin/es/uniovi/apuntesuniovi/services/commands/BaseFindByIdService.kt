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
) : AbstractCommand<List<Entity>>() {
    override fun execute(): List<Entity> {
        logService.info("execute() - start")
        if (ValidatorId(id).isValid()) {
            val optional = repository.findById(id)
            if (optional.isPresent) {
                logService.info("execute() - end")
                return listOf(optional.get())
            }
            logService.error("execute() - error")
            throw IllegalArgumentException(getMessageNotFound())
        }
        logService.error("execute() - error")
        throw IllegalArgumentException(getMessageInvalidId())
    }

    abstract fun getMessageNotFound(): String

    abstract fun getMessageInvalidId(): String
}