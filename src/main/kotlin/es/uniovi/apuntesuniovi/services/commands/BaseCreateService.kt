package es.uniovi.apuntesuniovi.services.commands

import es.uniovi.apuntesuniovi.infrastructure.AbstractCommand
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Create a entity in service layer
 */
abstract class BaseCreateService<Entity>(
    private val repository: JpaRepository<Entity, Long>,
    private val entity: Entity,
) : AbstractCommand<List<Entity>>() {

    override fun execute(): List<Entity> {
        logService.info("execute() - start")
        val result = repository.save(entity)
        logService.info("execute() - end")
        return listOf(result)
    }
}