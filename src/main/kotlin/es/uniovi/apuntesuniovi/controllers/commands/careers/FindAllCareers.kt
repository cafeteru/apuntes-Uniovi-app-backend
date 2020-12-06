package es.uniovi.apuntesuniovi.controllers.commands.careers

import es.uniovi.apuntesuniovi.infrastructure.AbstractCommand
import es.uniovi.apuntesuniovi.servicies.CareerService
import es.uniovi.apuntesuniovi.servicies.dtos.entities.CareerDto

/**
 * Return all careers in controller layer
 */
class FindAllCareers(
    private val careerService: CareerService
) : AbstractCommand<List<CareerDto>>() {
    override fun execute(): List<CareerDto> {
        logService.info("execute() - start")
        val result = careerService.findAll()
        logService.info("execute() - end")
        return result
    }
}