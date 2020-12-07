package es.uniovi.apuntesuniovi.services.commands.semesters

import es.uniovi.apuntesuniovi.models.Semester
import es.uniovi.apuntesuniovi.repositories.SemesterRepository
import es.uniovi.apuntesuniovi.services.commands.BaseFindAllService

/**
 * Return all careers in service layer
 */
class FindAllSemestersService(semesterRepository: SemesterRepository) :
    BaseFindAllService<Semester>(semesterRepository)