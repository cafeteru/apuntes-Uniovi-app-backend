package es.uniovi.apuntesuniovi.controllers.commands.careers

import com.google.gson.Gson
import es.uniovi.apuntesuniovi.controllers.commands.BaseCreate
import es.uniovi.apuntesuniovi.models.Career
import es.uniovi.apuntesuniovi.services.CareerService
import es.uniovi.apuntesuniovi.services.dtos.entities.CareerDto

/**
 * Create a career in controller layer
 */
class CreateCareer(
    careerService: CareerService,
    json: String
) : BaseCreate<Career, CareerDto>(careerService, json) {

    override fun getEntityFromJson(json: String): CareerDto {
        return Gson().fromJson(json, CareerDto::class.java)
    }
}