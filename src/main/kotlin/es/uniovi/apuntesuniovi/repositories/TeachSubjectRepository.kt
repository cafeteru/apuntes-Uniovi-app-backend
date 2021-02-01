package es.uniovi.apuntesuniovi.repositories

import es.uniovi.apuntesuniovi.models.TeachSubject
import org.springframework.data.repository.PagingAndSortingRepository

/**
 * Manage the TeachSubject table
 */
interface TeachSubjectRepository : PagingAndSortingRepository<TeachSubject, Long> {
  /**
   * Return by id of subject and teacher
   */
  fun existsBySubjectIdAndTeacherId(subjectId: Long, teacherId: Long): Boolean
}