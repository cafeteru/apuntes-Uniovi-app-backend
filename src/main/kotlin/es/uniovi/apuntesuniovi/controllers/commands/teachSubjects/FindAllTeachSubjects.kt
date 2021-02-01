package es.uniovi.apuntesuniovi.controllers.commands.teachSubjects

import es.uniovi.apuntesuniovi.controllers.commands.BaseFindAll
import es.uniovi.apuntesuniovi.models.TeachSubject
import es.uniovi.apuntesuniovi.services.TeachSubjectService
import es.uniovi.apuntesuniovi.services.dtos.entities.TeachSubjectDto
import org.springframework.data.domain.Pageable

/**
 * Return all TeachSubjects in controller layer
 */
class FindAllTeachSubjects(
  teachSubjectService: TeachSubjectService,
  pageable: Pageable
) : BaseFindAll<TeachSubject, TeachSubjectDto>(teachSubjectService, pageable)