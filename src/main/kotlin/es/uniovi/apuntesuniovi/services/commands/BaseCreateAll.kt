package es.uniovi.apuntesuniovi.services.commands

import es.uniovi.apuntesuniovi.infrastructure.AbstractCommand
import es.uniovi.apuntesuniovi.repositories.interfaces.PagingQueryDslRepository

/**
 * Create a entity in service layer
 */
abstract class BaseCreateAll<Entity>(
    private val repository: PagingQueryDslRepository<Entity>,
    private val entity: List<Entity>,
) : AbstractCommand<MutableIterable<Entity>>() {

    override fun execute(): MutableIterable<Entity> {
        logService.info("execute() - start")
        val result = repository.saveAll(entity)
        logService.info("execute() - end")
        return result
    }
}