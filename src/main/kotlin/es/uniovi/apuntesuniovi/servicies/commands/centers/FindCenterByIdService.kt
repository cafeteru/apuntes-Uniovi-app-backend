package es.uniovi.apuntesuniovi.servicies.commands.centers

import es.uniovi.apuntesuniovi.entities.Center
import es.uniovi.apuntesuniovi.repositories.CenterRepository
import es.uniovi.apuntesuniovi.servicies.commands.AbstractFindById

/**
 * Return center by id in service layer
 */
class FindCenterByIdService(
    centerRepository: CenterRepository,
    id: Long
) : AbstractFindById<Center>(centerRepository, id)