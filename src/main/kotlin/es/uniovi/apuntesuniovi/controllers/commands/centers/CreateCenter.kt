package es.uniovi.apuntesuniovi.controllers.commands.centers

import com.google.gson.Gson
import es.uniovi.apuntesuniovi.infrastructure.AbstractCommand
import es.uniovi.apuntesuniovi.servicies.CenterService
import es.uniovi.apuntesuniovi.servicies.dtos.entities.CenterDto

/**
 * Create a center in controller layer
 */
class CreateCenter(
    private val centerService: CenterService,
    private val json: String
) : AbstractCommand<List<CenterDto>>() {
    override fun execute(): List<CenterDto> {
        logService.info("execute() - start")
        val userDto = Gson().fromJson(json, CenterDto::class.java)
        val result = centerService.create(userDto)
        logService.info("execute() - end")
        return result
    }
}