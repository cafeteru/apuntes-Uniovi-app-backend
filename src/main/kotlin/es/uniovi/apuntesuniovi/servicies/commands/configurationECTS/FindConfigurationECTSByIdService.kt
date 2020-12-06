package es.uniovi.apuntesuniovi.servicies.commands.configurationECTS

import es.uniovi.apuntesuniovi.entities.ConfigurationECTS
import es.uniovi.apuntesuniovi.repositories.ConfigurationECTSRepository
import es.uniovi.apuntesuniovi.servicies.commands.AbstractFindById

/**
 * Return ConfigurationECTS by id in service layer
 */
class FindConfigurationECTSByIdService(
    configurationECTSRepository: ConfigurationECTSRepository,
    id: Long
) : AbstractFindById<ConfigurationECTS>(configurationECTSRepository, id)