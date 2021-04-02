package es.uniovi.apuntesuniovi.services.commands

import es.uniovi.apuntesuniovi.infrastructure.AbstractCommand
import es.uniovi.apuntesuniovi.repositories.PagingQueryDslRepository
import es.uniovi.apuntesuniovi.validators.impl.ValidatorId
import org.springframework.util.Assert

/**
 * Delete a entity
 */
abstract class BaseDelete<Entity>(
    private val repository: PagingQueryDslRepository<Entity>,
    private val id: Long,
) : AbstractCommand<Boolean>() {

    override fun execute(): Boolean {
        Assert.isTrue(ValidatorId(id).isValid(), getMessageInvalidId())
        val optional = repository.findById(id)
        Assert.isTrue(optional.isPresent, getMessageNotFound())
        repository.deleteById(id)
        return true
    }

    abstract fun getMessageInvalidId(): String

    abstract fun getMessageNotFound(): String

}