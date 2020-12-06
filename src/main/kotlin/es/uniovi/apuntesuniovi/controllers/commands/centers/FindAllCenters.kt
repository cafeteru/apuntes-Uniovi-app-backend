package es.uniovi.apuntesuniovi.controllers.commands.centers

import es.uniovi.apuntesuniovi.infrastructure.AbstractCommand
import es.uniovi.apuntesuniovi.services.CenterService
import es.uniovi.apuntesuniovi.services.dtos.entities.CenterDto

/**
 * Return all centers in controller layer
 */
class FindAllCenters(
    private val centerService: CenterService
) : AbstractCommand<List<CenterDto>>() {
    override fun execute(): List<CenterDto> {
        logService.info("execute() - start")
        val result = centerService.findAll()
        logService.info("execute() - end")
        return result
    }
}