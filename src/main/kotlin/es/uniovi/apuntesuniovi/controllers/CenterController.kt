package es.uniovi.apuntesuniovi.controllers

import es.uniovi.apuntesuniovi.controllers.commands.centers.CreateCenter
import es.uniovi.apuntesuniovi.controllers.commands.centers.FindAllCenters
import es.uniovi.apuntesuniovi.infrastructure.AbstractCommand
import es.uniovi.apuntesuniovi.services.BaseService
import es.uniovi.apuntesuniovi.services.CenterService
import es.uniovi.apuntesuniovi.services.dtos.entities.CenterDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Define centers endpoints
 */
@RestController
@RequestMapping("/centers")
class CenterController @Autowired constructor(
    private val centerService: CenterService
) : BaseController<CenterDto>(centerService) {

    override fun getCreateCommand(baseService: BaseService<CenterDto>, json: String): AbstractCommand<List<CenterDto>> {
        return CreateCenter(centerService, json)
    }

    override fun getFindAllCommand(baseService: BaseService<CenterDto>): AbstractCommand<List<CenterDto>> {
        return FindAllCenters(centerService)
    }
}