package es.uniovi.apuntesuniovi.controllers

import es.uniovi.apuntesuniovi.controllers.commands.careers.CreateCareer
import es.uniovi.apuntesuniovi.controllers.commands.careers.FindAllCareers
import es.uniovi.apuntesuniovi.models.Career
import es.uniovi.apuntesuniovi.services.BaseService
import es.uniovi.apuntesuniovi.services.CareerService
import es.uniovi.apuntesuniovi.services.dtos.entities.CareerDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Define careers endpoints
 */
@RestController
@RequestMapping("/careers")
class CareerController @Autowired constructor(
    private val careerService: CareerService
) : BaseController<Career, CareerDto>(careerService) {

    override fun findAll(baseService: BaseService<Career, CareerDto>): List<CareerDto> {
        return FindAllCareers(careerService).execute()
    }

    override fun create(baseService: BaseService<Career, CareerDto>, json: String): List<CareerDto> {
        return CreateCareer(careerService, json).execute()
    }
}