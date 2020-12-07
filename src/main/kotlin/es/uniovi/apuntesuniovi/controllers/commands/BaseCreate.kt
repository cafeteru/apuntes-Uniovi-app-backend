package es.uniovi.apuntesuniovi.controllers.commands

import es.uniovi.apuntesuniovi.infrastructure.AbstractCommand
import es.uniovi.apuntesuniovi.services.BaseService

/**
 * Save a user in controller layer
 */
abstract class BaseCreate<Dto>(
    private val service: BaseService<Dto>,
    private val json: String
) : AbstractCommand<List<Dto>>() {

    override fun execute(): List<Dto> {
        logService.info("execute() - start")
        val dto = getEntityFromJson(json)
        val result = service.create(dto)
        logService.info("execute() - end")
        return result
    }

    /**
     * Convert a JSON to dto
     */
    abstract fun getEntityFromJson(json: String): Dto
}