package es.uniovi.apuntesuniovi.services.commands.centers

import es.uniovi.apuntesuniovi.models.Center
import es.uniovi.apuntesuniovi.repositories.CenterRepository
import es.uniovi.apuntesuniovi.services.commands.BaseCreateService

/**
 * Create a center in service layer
 */
class CreateCenter(centerRepository: CenterRepository, center: Center) :
  BaseCreateService<Center>(centerRepository, center)