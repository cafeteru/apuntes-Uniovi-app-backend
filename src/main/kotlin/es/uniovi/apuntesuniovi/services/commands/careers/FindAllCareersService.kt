package es.uniovi.apuntesuniovi.services.commands.careers

import es.uniovi.apuntesuniovi.models.Career
import es.uniovi.apuntesuniovi.repositories.CareerRepository
import es.uniovi.apuntesuniovi.services.commands.BaseFindAllService
import org.springframework.data.domain.Pageable

/**
 * Return all careers in service layer
 */
class FindAllCareersService(
    careerRepository: CareerRepository,
    pageable: Pageable
) : BaseFindAllService<Career>(careerRepository, pageable)