package es.uniovi.apuntesuniovi.services

import es.uniovi.apuntesuniovi.repositories.SemesterRepository
import es.uniovi.apuntesuniovi.services.commands.semesters.CreateSemesterService
import es.uniovi.apuntesuniovi.services.commands.semesters.FindAllSemestersService
import es.uniovi.apuntesuniovi.services.dtos.assemblers.SemesterAssembler
import es.uniovi.apuntesuniovi.services.dtos.entities.SemesterDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Service to manage semesters
 */
@Service
class SemesterService @Autowired constructor(
    private val semesterRepository: SemesterRepository,
    private val semesterAssembler: SemesterAssembler
) : BaseService<SemesterDto>() {

    override fun create(dto: SemesterDto): List<SemesterDto> {
        logService.info("create(dto: CareerDto) - start")
        val course = semesterAssembler.dtoToEntity(dto)
        val result = CreateSemesterService(semesterRepository, course).execute()
        logService.info("create(dto: CareerDto) - end")
        return semesterAssembler.listToDto(result)
    }

    override fun findAll(): List<SemesterDto> {
        logService.info("findAll() - start")
        val result = FindAllSemestersService(semesterRepository).execute()
        logService.info("findAll() - end")
        return semesterAssembler.listToDto(result)
    }
}