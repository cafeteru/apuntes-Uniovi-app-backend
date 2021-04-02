package es.uniovi.apuntesuniovi.services.commands

import es.uniovi.apuntesuniovi.infrastructure.AbstractCommand
import es.uniovi.apuntesuniovi.repositories.PagingQueryDslRepository
import org.springframework.util.Assert

abstract class BaseUpdate<Entity>(
        private val repository: PagingQueryDslRepository<Entity>,
        private val id: Long,
        private val entity: Entity,
) : AbstractCommand<Entity>() {
    protected var original: Entity? = null

    override fun execute(): Entity {
        logService.info("execute() - start")
        checkExists()
        checkData()
        val result = repository.save(entity!!)
        logService.info("execute() - end")
        return result
    }

    private fun checkExists() {
        logService.info("checkExists() - start")
        val optional = repository.findById(id)
        Assert.isTrue(optional.isPresent, getMessageNotFound())
        original = optional.get()
        logService.info("checkExists() - end")
    }

    /**
     * Check the data
     */
    abstract fun checkData()

    abstract fun getMessageNotFound(): String
}