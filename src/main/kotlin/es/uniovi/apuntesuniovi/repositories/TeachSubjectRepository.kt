package es.uniovi.apuntesuniovi.repositories

import es.uniovi.apuntesuniovi.models.TeachSubject
import org.springframework.data.repository.PagingAndSortingRepository
import java.util.*

/**
 * Manage the teachSubject table
 */
interface TeachSubjectRepository : PagingAndSortingRepository<TeachSubject, Long> {
    /**
     * Return by id of subject and teacher
     */
    fun findBySubjectIdAndTeacherId(subjectId: Long, teacherId: Long): Optional<TeachSubject>
}