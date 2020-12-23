package es.uniovi.apuntesuniovi.repositories

import es.uniovi.apuntesuniovi.models.UnitSubject
import org.springframework.data.repository.PagingAndSortingRepository

/**
 * Manage the UnitSubject table
 */
interface UnitSubjectRepository : PagingAndSortingRepository<UnitSubject, Long> {
    fun existsByNameAndSubjectId(name: String, subjectId: Long): Boolean
    fun existsByPositionAndSubjectId(position: Int, subjectId: Long): Boolean
}