package es.uniovi.apuntesuniovi.services.commands.centers

import es.uniovi.apuntesuniovi.models.Center
import es.uniovi.apuntesuniovi.repositories.CenterRepository
import es.uniovi.apuntesuniovi.services.commands.BaseFindByIdService

/**
 * Return center by id in service layer
 */
class FindCenterByIdService(
    centerRepository: CenterRepository,
    id: Long
) : BaseFindByIdService<Center>(centerRepository, id)