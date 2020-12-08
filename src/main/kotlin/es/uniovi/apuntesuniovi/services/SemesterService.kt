package es.uniovi.apuntesuniovi.services

import es.uniovi.apuntesuniovi.models.Semester
import es.uniovi.apuntesuniovi.repositories.SemesterRepository
import es.uniovi.apuntesuniovi.services.commands.semesters.CreateSemesterService
import es.uniovi.apuntesuniovi.services.commands.semesters.FindAllSemestersService
import es.uniovi.apuntesuniovi.services.dtos.assemblers.SemesterAssembler
import es.uniovi.apuntesuniovi.services.dtos.entities.SemesterDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Service

/**
 * Service to manage semesters
 */
@Service
class SemesterService @Autowired constructor(
    private val semesterRepository: SemesterRepository,
    semesterAssembler: SemesterAssembler
) : BaseService<Semester, SemesterDto>(semesterRepository, semesterAssembler) {

    override fun create(
        repository: PagingAndSortingRepository<Semester, Long>,
        entity: Semester
    ): List<Semester> {
        return CreateSemesterService(semesterRepository, entity).execute()
    }

    override fun findAll(
        repository: PagingAndSortingRepository<Semester, Long>,
        pageable: Pageable
    ): Page<Semester> {
        return FindAllSemestersService(semesterRepository, pageable).execute()
    }
}