package es.uniovi.apuntesuniovi.services.commands.semesters

import es.uniovi.apuntesuniovi.models.Semester
import es.uniovi.apuntesuniovi.repositories.SemesterRepository
import es.uniovi.apuntesuniovi.services.commands.BaseFindAllService
import org.springframework.data.domain.Pageable

/**
 * Return all careers in service layer
 */
class FindAllSemesters(
  semesterRepository: SemesterRepository,
  pageable: Pageable
) : BaseFindAllService<Semester>(semesterRepository, pageable)