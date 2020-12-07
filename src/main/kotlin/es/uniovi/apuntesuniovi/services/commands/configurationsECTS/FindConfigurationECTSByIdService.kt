package es.uniovi.apuntesuniovi.services.commands.configurationsECTS

import es.uniovi.apuntesuniovi.models.ConfigurationECTS
import es.uniovi.apuntesuniovi.repositories.ConfigurationECTSRepository
import es.uniovi.apuntesuniovi.services.commands.BaseFindByIdService

/**
 * Return ConfigurationECTS by id in service layer
 */
class FindConfigurationECTSByIdService(
    configurationECTSRepository: ConfigurationECTSRepository,
    id: Long
) : BaseFindByIdService<ConfigurationECTS>(configurationECTSRepository, id)