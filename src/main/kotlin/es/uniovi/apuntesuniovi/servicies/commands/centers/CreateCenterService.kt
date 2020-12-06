package es.uniovi.apuntesuniovi.servicies.commands.centers

import es.uniovi.apuntesuniovi.entities.Center
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