package es.uniovi.apuntesuniovi.services.commands.subjects

import es.uniovi.apuntesuniovi.models.Subject
import es.uniovi.apuntesuniovi.repositories.SubjectRepository
import es.uniovi.apuntesuniovi.services.commands.BaseFindAllService
import org.springframework.data.domain.Pageable

/**
 * Return all subjects in service layer
 */
class FindAllSubjectsService(
  subjectRepository: SubjectRepository,
  pageable: Pageable
) : BaseFindAllService<Subject>(subjectRepository, pageable)