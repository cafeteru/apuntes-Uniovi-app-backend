package es.uniovi.apuntesuniovi.controllers.commands.subjects

import es.uniovi.apuntesuniovi.controllers.commands.BaseFindAll
import es.uniovi.apuntesuniovi.models.Subject
import es.uniovi.apuntesuniovi.services.SubjectService
import es.uniovi.apuntesuniovi.services.dtos.entities.SubjectDto
import org.springframework.data.domain.Pageable

/**
 * Return all subjects in controller layer
 */
class FindAllSubjects(
  subjectService: SubjectService,
  pageable: Pageable
) : BaseFindAll<Subject, SubjectDto>(subjectService, pageable)