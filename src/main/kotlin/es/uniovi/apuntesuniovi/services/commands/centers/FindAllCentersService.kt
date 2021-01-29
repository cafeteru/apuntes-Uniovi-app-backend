package es.uniovi.apuntesuniovi.services.commands.centers

import es.uniovi.apuntesuniovi.models.Center
import es.uniovi.apuntesuniovi.repositories.CenterRepository
import es.uniovi.apuntesuniovi.services.commands.BaseFindAllService
import org.springframework.data.domain.Pageable

/**
 * Return all centers in service layer
 */
class FindAllCentersService(
  centerRepository: CenterRepository,
  pageable: Pageable
) : BaseFindAllService<Center>(centerRepository, pageable)