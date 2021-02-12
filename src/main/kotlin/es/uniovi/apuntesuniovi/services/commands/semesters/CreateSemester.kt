package es.uniovi.apuntesuniovi.services.commands.semesters

import es.uniovi.apuntesuniovi.models.Semester
import es.uniovi.apuntesuniovi.repositories.SemesterRepository
import es.uniovi.apuntesuniovi.services.commands.BaseCreateService

/**
 * Create a semester in service layer
 */
class CreateSemester(semesterRepository: SemesterRepository, semester: Semester) :
  BaseCreateService<Semester>(semesterRepository, semester) {

  override fun checkData() {
    logService.info("checkData() - start")
    logService.info("checkData() - end")
  }
}