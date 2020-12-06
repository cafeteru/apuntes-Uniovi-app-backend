package es.uniovi.apuntesuniovi.controllers.commands.centers

import com.google.gson.Gson
import es.uniovi.apuntesuniovi.infrastructure.Command
import es.uniovi.apuntesuniovi.infrastructure.log.LogService
import es.uniovi.apuntesuniovi.servicies.CenterService
import es.uniovi.apuntesuniovi.servicies.dtos.entities.CenterDto

/**
 * Create a center in service layer
 */
class CreateCenter(
    private val centerService: CenterService,
    private val json: String
) : Command<List<CenterDto>> {
    private val logService = LogService(this.javaClass)

    override fun execute(): List<CenterDto> {
        logService.info("execute() - start")
        val userDto = Gson().fromJson(json, CenterDto::class.java)
        val result = centerService.create(userDto)
        logService.info("execute() - end")
        return result
    }

}