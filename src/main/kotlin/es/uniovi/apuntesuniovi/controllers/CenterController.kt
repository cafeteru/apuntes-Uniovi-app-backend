package es.uniovi.apuntesuniovi.controllers

import es.uniovi.apuntesuniovi.controllers.commands.centers.CreateCenter
import es.uniovi.apuntesuniovi.controllers.commands.centers.FindAllCenters
import es.uniovi.apuntesuniovi.models.Center
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
) : BaseController<Center, CenterDto>(centerService) {

    override fun create(baseService: BaseService<Center, CenterDto>, json: String): List<CenterDto> {
        return CreateCenter(centerService, json).execute()
    }

    override fun findAll(baseService: BaseService<Center, CenterDto>): List<CenterDto> {
        return FindAllCenters(centerService).execute()
    }
}