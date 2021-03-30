package es.uniovi.apuntesuniovi.services.commands

import es.uniovi.apuntesuniovi.infrastructure.AbstractCommand
import es.uniovi.apuntesuniovi.validators.impl.ValidatorId
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.util.Assert

/**
 * Find entity by id
 */
abstract class BaseFindByIdService<Entity>(
    private val repository: PagingAndSortingRepository<Entity, Long>,
    private val id: Long
) : AbstractCommand<Entity>() {

    override fun execute(): Entity {
        logService.info("execute() - start")
        Assert.isTrue(ValidatorId(id).isValid(), getMessageInvalidId())
        val optional = repository.findById(id)
        Assert.isTrue(optional.isPresent, getMessageNotFound())
        logService.info("execute() - end")
        return optional.get()
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