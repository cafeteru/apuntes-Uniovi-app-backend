package es.uniovi.apuntesuniovi.services.commands.teachSubjectRegistries

import es.uniovi.apuntesuniovi.models.TeachSubjectRegistry
import es.uniovi.apuntesuniovi.repositories.TeachSubjectRegistryRepository
import es.uniovi.apuntesuniovi.services.commands.BaseFindAllService
import org.springframework.data.domain.Pageable

/**
 * Return all TeachSubjectRegistry in service layer
 */
class FindAllTeachSubjectsRegistries(
    repository: TeachSubjectRegistryRepository,
    pageable: Pageable
) : BaseFindAllService<TeachSubjectRegistry>(repository, pageable)