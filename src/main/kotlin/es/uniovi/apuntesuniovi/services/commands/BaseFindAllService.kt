package es.uniovi.apuntesuniovi.services.commands

import es.uniovi.apuntesuniovi.infrastructure.AbstractCommand
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Return all entities in service layer
 */
abstract class BaseFindAllService<Entity>(
    private val repository: JpaRepository<Entity, Long>
) : AbstractCommand<List<Entity>>() {

    override fun execute(): List<Entity> {
        logService.info("execute() - start")
        val list = repository.findAll()
        logService.info("execute() - end")
        return list
    }
}