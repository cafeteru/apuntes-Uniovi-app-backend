package es.uniovi.apuntesuniovi.controllers.commands.careers

import es.uniovi.apuntesuniovi.controllers.commands.BaseFindAll
import es.uniovi.apuntesuniovi.models.Career
import es.uniovi.apuntesuniovi.services.CareerService
import es.uniovi.apuntesuniovi.services.dtos.entities.CareerDto

/**
 * Return all careers in controller layer
 */
class FindAllCareers(careerService: CareerService) : BaseFindAll<Career, CareerDto>(careerService)