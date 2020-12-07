package es.uniovi.apuntesuniovi.services.commands.teachSubjectRegistries

import es.uniovi.apuntesuniovi.models.TeachSubjectRegistry
import es.uniovi.apuntesuniovi.repositories.TeachSubjectRegistryRepository
import es.uniovi.apuntesuniovi.services.commands.BaseFindByIdService

/**
 * Return teachSubjectRegistry by id in service layer
 */
class FindTeachSubjectRegistryByIdService(
    teachSubjectRegistryRepository: TeachSubjectRegistryRepository,
    id: Long
) : BaseFindByIdService<TeachSubjectRegistry>(teachSubjectRegistryRepository, id)