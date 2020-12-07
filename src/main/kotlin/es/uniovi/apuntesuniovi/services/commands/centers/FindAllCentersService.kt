package es.uniovi.apuntesuniovi.services.commands.centers

import es.uniovi.apuntesuniovi.models.Center
import es.uniovi.apuntesuniovi.repositories.CenterRepository
import es.uniovi.apuntesuniovi.services.commands.BaseFindAllService

/**
 * Return all centers in service layer
 */
class FindAllCentersService(centerRepository: CenterRepository) : BaseFindAllService<Center>(centerRepository)