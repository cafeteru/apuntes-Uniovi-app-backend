package es.uniovi.apuntesuniovi.controllers.commands.centers

import es.uniovi.apuntesuniovi.infrastructure.Command
import es.uniovi.apuntesuniovi.infrastructure.log.LogService
import es.uniovi.apuntesuniovi.servicies.CenterService
import es.uniovi.apuntesuniovi.servicies.dtos.entities.CenterDto

/**
 * Return all centers in controller layer
 */
class FindAllCenters(
    private val centerService: CenterService
) : Command<List<CenterDto>> {
    private val logService = LogService(this.javaClass)

    override fun execute(): List<CenterDto> {
        logService.info("execute() - start")
        val result = centerService.findAll()
        logService.info("execute() - end")
        return result
    }

}