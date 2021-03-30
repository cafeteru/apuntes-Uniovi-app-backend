package es.uniovi.apuntesuniovi.services

import es.uniovi.apuntesuniovi.dtos.assemblers.UnitSubjectAssembler
import es.uniovi.apuntesuniovi.dtos.entities.UnitSubjectDto
import es.uniovi.apuntesuniovi.models.UnitSubject
import es.uniovi.apuntesuniovi.repositories.UnitSubjectRepository
import es.uniovi.apuntesuniovi.services.commands.unitSubjects.CreateUnitSubject
import es.uniovi.apuntesuniovi.services.commands.unitSubjects.FindAllUnitSubjects
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Service

/**
 * Service to manage units of subjects
 */
@Service
class UnitSubjectService @Autowired constructor(
    private val unitSubjectRepository: UnitSubjectRepository,
    unitSubjectAssembler: UnitSubjectAssembler
) : BaseService<UnitSubject, UnitSubjectDto>(unitSubjectRepository, unitSubjectAssembler) {

    override fun create(
        repository: PagingAndSortingRepository<UnitSubject, Long>,
        entity: UnitSubject
    ): UnitSubject {
        return CreateUnitSubject(unitSubjectRepository, entity).execute()
    }

    override fun findAll(
        repository: PagingAndSortingRepository<UnitSubject, Long>,
        pageable: Pageable
    ): Page<UnitSubject> {
        return FindAllUnitSubjects(unitSubjectRepository, pageable).execute()
    }
}