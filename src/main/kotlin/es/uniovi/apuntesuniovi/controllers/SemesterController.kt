package es.uniovi.apuntesuniovi.controllers

import es.uniovi.apuntesuniovi.controllers.commands.semesters.CreateSemester
import es.uniovi.apuntesuniovi.controllers.commands.semesters.FindAllSemesters
import es.uniovi.apuntesuniovi.infrastructure.AbstractCommand
import es.uniovi.apuntesuniovi.services.BaseService
import es.uniovi.apuntesuniovi.services.SemesterService
import es.uniovi.apuntesuniovi.services.dtos.entities.SemesterDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Define semesters endpoints
 */
@RestController
@RequestMapping("/semesters")
class SemesterController @Autowired constructor(
    private val semesterService: SemesterService
) : BaseController<SemesterDto>(semesterService) {

    override fun getCreateCommand(
        baseService: BaseService<SemesterDto>,
        json: String
    ): AbstractCommand<List<SemesterDto>> {
        return CreateSemester(semesterService, json)
    }

    override fun getFindAllCommand(baseService: BaseService<SemesterDto>): AbstractCommand<List<SemesterDto>> {
        return FindAllSemesters(semesterService)
    }
}