package es.uniovi.apuntesuniovi.controllers.commands.careers

import com.google.gson.Gson
import es.uniovi.apuntesuniovi.controllers.commands.AbstractCreate
import es.uniovi.apuntesuniovi.services.CareerService
import es.uniovi.apuntesuniovi.services.dtos.entities.CareerDto

/**
 * Create a career in controller layer
 */
class CreateCareer(
    careerService: CareerService,
    json: String
) : AbstractCreate<CareerDto>(careerService, json) {

    override fun getEntityFromJson(json: String): CareerDto {
        logService.info("getEntityFromJson(json: String) - start")
        val dto = Gson().fromJson(json, CareerDto::class.java)
        logService.info("getEntityFromJson(json: String) - end")
        return dto
    }
}