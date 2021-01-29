package es.uniovi.apuntesuniovi.services

import es.uniovi.apuntesuniovi.models.Career
import es.uniovi.apuntesuniovi.repositories.CareerRepository
import es.uniovi.apuntesuniovi.services.commands.careers.CreateCareerService
import es.uniovi.apuntesuniovi.services.commands.careers.FindAllCareersService
import es.uniovi.apuntesuniovi.services.dtos.assemblers.CareerAssembler
import es.uniovi.apuntesuniovi.services.dtos.entities.CareerDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Service

/**
 * Service to manage careers
 */
@Service
class CareerService @Autowired constructor(
  private val careerRepository: CareerRepository,
  careerAssembler: CareerAssembler
) : BaseService<Career, CareerDto>(careerRepository, careerAssembler) {

  override fun create(
    repository: PagingAndSortingRepository<Career, Long>,
    entity: Career
  ): Career {
    return CreateCareerService(careerRepository, entity).execute()
  }

  override fun findAll(
    repository: PagingAndSortingRepository<Career, Long>,
    pageable: Pageable
  ): Page<Career> {
    return FindAllCareersService(careerRepository, pageable).execute()
  }
}