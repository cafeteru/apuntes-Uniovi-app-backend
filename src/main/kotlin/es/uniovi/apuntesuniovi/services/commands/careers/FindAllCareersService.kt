package es.uniovi.apuntesuniovi.services.commands.careers

import es.uniovi.apuntesuniovi.models.Career
import es.uniovi.apuntesuniovi.repositories.CareerRepository
import es.uniovi.apuntesuniovi.services.commands.BaseFindAllService

/**
 * Return all careers in service layer
 */
class FindAllCareersService(careerRepository: CareerRepository) : BaseFindAllService<Career>(careerRepository)