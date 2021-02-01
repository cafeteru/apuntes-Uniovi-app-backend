package es.uniovi.apuntesuniovi.controllers.commands.unitSubjects

import es.uniovi.apuntesuniovi.controllers.commands.BaseFindAll
import es.uniovi.apuntesuniovi.models.UnitSubject
import es.uniovi.apuntesuniovi.services.UnitSubjectService
import es.uniovi.apuntesuniovi.services.dtos.entities.UnitSubjectDto
import org.springframework.data.domain.Pageable

/**
 * Return all UnitSubjects in controller layer
 */
class FindAllUnitSubjects(
  unitSubjectService: UnitSubjectService,
  pageable: Pageable
) : BaseFindAll<UnitSubject, UnitSubjectDto>(unitSubjectService, pageable)