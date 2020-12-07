package es.uniovi.apuntesuniovi.controllers.commands.centers

import com.google.gson.Gson
import es.uniovi.apuntesuniovi.controllers.commands.AbstractCreate
import es.uniovi.apuntesuniovi.services.CenterService
import es.uniovi.apuntesuniovi.services.dtos.entities.CenterDto

/**
 * Create a center in controller layer
 */
class CreateCenter(
    centerService: CenterService,
    json: String
) : AbstractCreate<CenterDto>(centerService, json) {

    override fun getEntityFromJson(json: String): CenterDto {
        logService.info("getEntityFromJson(json: String) - start")
        val dto = Gson().fromJson(json, CenterDto::class.java)
        logService.info("getEntityFromJson(json: String) - end")
        return dto
    }
}