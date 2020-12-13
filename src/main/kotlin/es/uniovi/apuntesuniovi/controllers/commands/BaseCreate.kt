package es.uniovi.apuntesuniovi.controllers.commands

import com.google.gson.JsonSyntaxException
import es.uniovi.apuntesuniovi.infrastructure.AbstractCommand
import es.uniovi.apuntesuniovi.services.BaseService

/**
 * Save a user in controller layer
 */
abstract class BaseCreate<Entity, Dto>(
    private val service: BaseService<Entity, Dto>,
    private val json: String
) : AbstractCommand<Dto>() {

    override fun execute(): Dto {
        logService.info("execute() - start")
        try {
            val dto = getEntityFromJson(json)
            val result = service.create(dto)
            logService.info("execute() - end")
            return result
        } catch (e: JsonSyntaxException) {
            logService.error("execute() - error")
            throw IllegalArgumentException(getMessageInvalidJson())
        }
    }

    /**
     * Convert a JSON to dto
     */
    abstract fun getEntityFromJson(json: String): Dto

    /**
     * Return exception message when invalid json
     */
    abstract fun getMessageInvalidJson(): String
}