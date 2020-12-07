package es.uniovi.apuntesuniovi.services.commands.careers

import es.uniovi.apuntesuniovi.models.Career
import es.uniovi.apuntesuniovi.repositories.CareerRepository
import es.uniovi.apuntesuniovi.services.commands.BaseCreateService

/**
 * Create a career in service layer
 */
class CreateCareerService(careerRepository: CareerRepository, career: Career) :
    BaseCreateService<Career>(careerRepository, career)