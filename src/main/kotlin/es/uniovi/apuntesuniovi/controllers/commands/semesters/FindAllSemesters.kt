package es.uniovi.apuntesuniovi.controllers.commands.semesters

import es.uniovi.apuntesuniovi.controllers.commands.BaseFindAll
import es.uniovi.apuntesuniovi.models.Semester
import es.uniovi.apuntesuniovi.services.SemesterService
import es.uniovi.apuntesuniovi.services.dtos.entities.SemesterDto

/**
 * Return all semesters in controller layer
 */
class FindAllSemesters(semesterService: SemesterService) : BaseFindAll<Semester, SemesterDto>(semesterService)