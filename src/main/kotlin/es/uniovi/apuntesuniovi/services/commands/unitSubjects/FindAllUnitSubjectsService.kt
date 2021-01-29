package es.uniovi.apuntesuniovi.services.commands.unitSubjects

import es.uniovi.apuntesuniovi.models.UnitSubject
import es.uniovi.apuntesuniovi.repositories.UnitSubjectRepository
import es.uniovi.apuntesuniovi.services.commands.BaseFindAllService
import org.springframework.data.domain.Pageable

/**
 * Return all units of subjects in service layer
 */
class FindAllUnitSubjectsService(
  unitSubjectRepository: UnitSubjectRepository,
  pageable: Pageable
) : BaseFindAllService<UnitSubject>(unitSubjectRepository, pageable)