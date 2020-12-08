package es.uniovi.apuntesuniovi.controllers.commands.centers

import es.uniovi.apuntesuniovi.controllers.commands.BaseFindAll
import es.uniovi.apuntesuniovi.models.Center
import es.uniovi.apuntesuniovi.services.CenterService
import es.uniovi.apuntesuniovi.services.dtos.entities.CenterDto

/**
 * Return all centers in controller layer
 */
class FindAllCenters(centerService: CenterService) : BaseFindAll<Center, CenterDto>(centerService)