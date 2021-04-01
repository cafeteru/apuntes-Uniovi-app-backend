package es.uniovi.apuntesuniovi.services.commands

import es.uniovi.apuntesuniovi.infrastructure.AbstractCommand
import es.uniovi.apuntesuniovi.repositories.PagingQueryDslRepository

abstract class BaseUpdateCommand<Entity>(
    private val repository: PagingQueryDslRepository<Entity>,
    private val id: Long,
    private val entity: Entity,
) : AbstractCommand<Entity>() {

    override fun execute(): Entity {
        logService.info("execute() - start")

        checkData()
        val result = repository.save(entity!!)
        logService.info("execute() - end")
        return result
    }

    private fun checkExists(){
        val optional = repository.findById(id)

    }

    /**
     * Check the data
     */
    abstract fun checkData()
}