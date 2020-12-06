package es.uniovi.apuntesuniovi.servicies.commands.centers

import es.uniovi.apuntesuniovi.entities.Center
import es.uniovi.apuntesuniovi.infrastructure.Command
import es.uniovi.apuntesuniovi.infrastructure.log.LogService
import es.uniovi.apuntesuniovi.repositories.CenterRepository

/**
 * Return all centers in service layer
 */
class FindAllCentersService(
    private val centerRepository: CenterRepository
) : Command<List<Center>> {
    private val logService = LogService(this.javaClass)

    override fun execute(): List<Center> {
        logService.info("execute() - start")
        val list = centerRepository.findAll()
        logService.info("execute() - end")
        return list
    }
}