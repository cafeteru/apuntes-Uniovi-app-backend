package es.uniovi.apuntesuniovi.controllers.commands.centers

import es.uniovi.apuntesuniovi.controllers.commands.AbstractFindAll
import es.uniovi.apuntesuniovi.services.CenterService
import es.uniovi.apuntesuniovi.services.dtos.entities.CenterDto

/**
 * Return all centers in controller layer
 */
class FindAllCenters(centerService: CenterService) : AbstractFindAll<CenterDto>(centerService)