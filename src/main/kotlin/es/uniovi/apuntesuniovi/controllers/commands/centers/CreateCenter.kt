package es.uniovi.apuntesuniovi.controllers.commands.centers

import com.google.gson.Gson
import es.uniovi.apuntesuniovi.controllers.commands.BaseCreate
import es.uniovi.apuntesuniovi.models.Center
import es.uniovi.apuntesuniovi.services.CenterService
import es.uniovi.apuntesuniovi.services.dtos.entities.CenterDto

/**
 * Create a center in controller layer
 */
class CreateCenter(
    centerService: CenterService,
    json: String
) : BaseCreate<Center, CenterDto>(centerService, json) {

    override fun getEntityFromJson(json: String): CenterDto {
        return Gson().fromJson(json, CenterDto::class.java)
    }
}