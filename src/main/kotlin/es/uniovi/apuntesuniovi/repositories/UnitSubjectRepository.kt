package es.uniovi.apuntesuniovi.repositories

import es.uniovi.apuntesuniovi.models.UnitSubject
import org.springframework.data.repository.PagingAndSortingRepository

/**
 * Manage the UnitSubject table
 */
interface UnitSubjectRepository : PagingAndSortingRepository<UnitSubject, Long> {
  /**
   * Check if it exist by name and subject
   */
  fun existsByNameAndSubjectId(name: String, subjectId: Long): Boolean

  /**
   * Check if it exist by position and subject
   */
  fun existsByPositionAndSubjectId(position: Int, subjectId: Long): Boolean
}