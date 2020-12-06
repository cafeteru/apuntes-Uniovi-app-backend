package es.uniovi.apuntesuniovi.services.commands.careers

import es.uniovi.apuntesuniovi.models.Career
import es.uniovi.apuntesuniovi.infrastructure.AbstractCommand
import es.uniovi.apuntesuniovi.repositories.CareerRepository

/**
 * Return all careers in service layer
 */
class FindAllCareersService(
    private val careerRepository: CareerRepository
) : AbstractCommand<List<Career>>() {
    override fun execute(): List<Career> {
        logService.info("execute() - start")
        val list = careerRepository.findAll()
        logService.info("execute() - end")
        return list
    }
}