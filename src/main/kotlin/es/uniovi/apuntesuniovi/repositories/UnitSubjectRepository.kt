package es.uniovi.apuntesuniovi.repositories

import es.uniovi.apuntesuniovi.models.UnitSubject
import es.uniovi.apuntesuniovi.models.User
import org.springframework.data.repository.PagingAndSortingRepository

/**
 * Manage the UnitSubject table
 */
interface UnitSubjectRepository : PagingQueryDslRepository<UnitSubject>  {
    /**
     * Check if it exist by name and subject
     */
    fun existsByNameAndSubjectId(name: String, subjectId: Long): Boolean

    /**
     * Check if it exist by position and subject
     */
    fun existsByPositionAndSubjectId(position: Int, subjectId: Long): Boolean
}