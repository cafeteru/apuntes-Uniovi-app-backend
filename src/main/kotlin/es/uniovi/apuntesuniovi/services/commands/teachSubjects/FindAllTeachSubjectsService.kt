package es.uniovi.apuntesuniovi.services.commands.teachSubjects

import es.uniovi.apuntesuniovi.models.TeachSubject
import es.uniovi.apuntesuniovi.repositories.TeachSubjectRepository
import es.uniovi.apuntesuniovi.services.commands.BaseFindAllService
import org.springframework.data.domain.Pageable

/**
 * Return all TeachSubjects in service layer
 */
class FindAllTeachSubjectsService(
    repository: TeachSubjectRepository,
    pageable: Pageable
) : BaseFindAllService<TeachSubject>(repository, pageable)