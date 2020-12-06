package es.uniovi.apuntesuniovi.services.commands.centers

import es.uniovi.apuntesuniovi.models.Center
import es.uniovi.apuntesuniovi.infrastructure.AbstractCommand
import es.uniovi.apuntesuniovi.repositories.CenterRepository

/**
 * Create a center in service layer
 */
class CreateCenterService(
    private val centerRepository: CenterRepository,
    private val center: Center
) : AbstractCommand<List<Center>>() {
    override fun execute(): List<Center> {
        logService.info("execute() - start")
        val result = centerRepository.save(center)
        logService.info("execute() - end")
        return listOf(result)
    }
}