package es.uniovi.apuntesuniovi.services.commands.teachSubjectRegistries

import es.uniovi.apuntesuniovi.models.TeachSubjectRegistry
import es.uniovi.apuntesuniovi.repositories.TeachSubjectRegistryRepository
import es.uniovi.apuntesuniovi.services.commands.AbstractFindById

/**
 * Return teachSubjectRegistry by id in service layer
 */
class FindTeachSubjectRegistryByIdService(
    teachSubjectRegistryRepository: TeachSubjectRegistryRepository,
    id: Long
) : AbstractFindById<TeachSubjectRegistry>(teachSubjectRegistryRepository, id)