package es.uniovi.apuntesuniovi.repositories

import es.uniovi.apuntesuniovi.models.TeachSubject
import es.uniovi.apuntesuniovi.repositories.interfaces.PagingQueryDslRepository

/**
 * Manage the TeachSubject table
 */
interface TeachSubjectRepository : PagingQueryDslRepository<TeachSubject> {
    /**
     * Return by id of subject and teacher
     */
    fun existsBySubjectIdAndTeacherId(subjectId: Long, teacherId: Long): Boolean
}