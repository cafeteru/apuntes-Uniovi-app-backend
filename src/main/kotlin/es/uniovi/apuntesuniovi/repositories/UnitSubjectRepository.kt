package es.uniovi.apuntesuniovi.repositories

import es.uniovi.apuntesuniovi.models.UnitSubject
import es.uniovi.apuntesuniovi.repositories.interfaces.PagingQueryDslRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

/**
 * Manage the UnitSubject table
 */
interface UnitSubjectRepository : PagingQueryDslRepository<UnitSubject> {
    /**
     * Check if it exist by name and subject
     */
    fun existsByNameAndSubjectId(name: String, subjectId: Long): Boolean

    fun findBySubjectId(id: Long, pageable: Pageable): Page<UnitSubject>
}