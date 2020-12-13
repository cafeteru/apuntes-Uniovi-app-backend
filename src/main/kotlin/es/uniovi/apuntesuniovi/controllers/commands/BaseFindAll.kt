package es.uniovi.apuntesuniovi.controllers.commands

import es.uniovi.apuntesuniovi.infrastructure.AbstractCommand
import es.uniovi.apuntesuniovi.services.BaseService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

/**
 * Return all entities in controller layer
 */
abstract class BaseFindAll<Entity, Dto>(
    private val service: BaseService<Entity, Dto>,
    private val pageable: Pageable
) : AbstractCommand<Page<Dto>>() {

    override fun execute(): Page<Dto> {
        logService.info("execute() - start")
        val result = service.findAll(pageable)
        logService.info("execute() - end")
        return result
    }
}