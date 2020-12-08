package es.uniovi.apuntesuniovi.services

import es.uniovi.apuntesuniovi.models.Semester
import es.uniovi.apuntesuniovi.repositories.SemesterRepository
import es.uniovi.apuntesuniovi.services.commands.semesters.CreateSemesterService
import es.uniovi.apuntesuniovi.services.commands.semesters.FindAllSemestersService
import es.uniovi.apuntesuniovi.services.dtos.assemblers.SemesterAssembler
import es.uniovi.apuntesuniovi.services.dtos.entities.SemesterDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Service

/**
 * Service to manage semesters
 */
@Service
class SemesterService @Autowired constructor(
    private val semesterRepository: SemesterRepository,
    semesterAssembler: SemesterAssembler
) : BaseService<Semester, SemesterDto>(semesterRepository, semesterAssembler) {

    override fun create(repository: JpaRepository<Semester, Long>, entity: Semester): List<Semester> {
        return CreateSemesterService(semesterRepository, entity).execute()
    }

    override fun findAll(repository: JpaRepository<Semester, Long>): List<Semester> {
        return FindAllSemestersService(semesterRepository).execute()
    }
}