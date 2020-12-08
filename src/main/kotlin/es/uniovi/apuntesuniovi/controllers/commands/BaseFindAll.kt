package es.uniovi.apuntesuniovi.controllers.commands

import es.uniovi.apuntesuniovi.infrastructure.AbstractCommand
import es.uniovi.apuntesuniovi.services.BaseService

/**
 * Return all entities in controller layer
 */
abstract class BaseFindAll<Entity, Dto>(
    private val service: BaseService<Entity, Dto>
) : AbstractCommand<List<Dto>>() {

    override fun execute(): List<Dto> {
        logService.info("execute() - start")
        val result = service.findAll()
        logService.info("execute() - end")
        return result
    }
}