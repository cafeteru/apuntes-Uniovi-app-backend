package es.uniovi.apuntesuniovi.services.commands

import es.uniovi.apuntesuniovi.infrastructure.AbstractCommand
import es.uniovi.apuntesuniovi.repositories.PagingQueryDslRepository

/**
 * Create a entity in service layer
 */
abstract class BaseCreate<Entity>(
    private val repository: PagingQueryDslRepository<Entity>,
    private val entity: Entity,
) : AbstractCommand<Entity>() {

    override fun execute(): Entity {
        logService.info("execute() - start")
        checkData()
        val result = repository.save(entity!!)
        logService.info("execute() - end")
        return result
    }

    /**
     * Check the data
     */
    abstract fun checkData()
}