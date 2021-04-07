package es.uniovi.apuntesuniovi.services.commands

import es.uniovi.apuntesuniovi.infrastructure.AbstractCommand
import es.uniovi.apuntesuniovi.repositories.interfaces.PagingQueryDslRepository

/**
 * Update a list of entity in service layer
 */
abstract class BaseUpdateAll<Entity>(
    private val repository: PagingQueryDslRepository<Entity>,
    private val entity: List<Entity>,
) : AbstractCommand<MutableIterable<Entity>>() {

    override fun execute(): MutableIterable<Entity> {
        logService.info("execute() - start")
        checkData()
        checkExists()
        val result = repository.saveAll(entity)
        logService.info("execute() - end")
        return result
    }

    private fun checkExists() {
        logService.info("checkExists() - start")
        val list = getListEntity()
        if (list.isNotEmpty()) {
            repository.deleteAll(list)
        }
        logService.info("checkExists() - end")
    }

    abstract fun getListEntity(): List<Entity>

    abstract fun checkData()
}