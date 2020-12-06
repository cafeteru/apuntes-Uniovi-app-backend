package es.uniovi.apuntesuniovi.servicies.commands.centers

import es.uniovi.apuntesuniovi.entities.Center
import es.uniovi.apuntesuniovi.infrastructure.AbstractCommand
import es.uniovi.apuntesuniovi.repositories.CenterRepository

/**
 * Return all centers in service layer
 */
class FindAllCentersService(
    private val centerRepository: CenterRepository
) : AbstractCommand<List<Center>>() {
    override fun execute(): List<Center> {
        logService.info("execute() - start")
        val list = centerRepository.findAll()
        logService.info("execute() - end")
        return list
    }
}