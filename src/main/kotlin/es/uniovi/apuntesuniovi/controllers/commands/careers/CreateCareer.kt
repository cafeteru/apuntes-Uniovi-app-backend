package es.uniovi.apuntesuniovi.controllers.commands.careers

import com.google.gson.Gson
import es.uniovi.apuntesuniovi.infrastructure.AbstractCommand
import es.uniovi.apuntesuniovi.services.CareerService
import es.uniovi.apuntesuniovi.services.dtos.entities.CareerDto

/**
 * Create a center in controller layer
 */
class CreateCareer(
    private val careerService: CareerService,
    private val json: String
) : AbstractCommand<List<CareerDto>>() {
    override fun execute(): List<CareerDto> {
        logService.info("execute() - start")
        val careerDto = Gson().fromJson(json, CareerDto::class.java)
        val result = careerService.create(careerDto)
        logService.info("execute() - end")
        return result
    }
}