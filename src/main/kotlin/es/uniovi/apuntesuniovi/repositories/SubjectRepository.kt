package es.uniovi.apuntesuniovi.repositories

import es.uniovi.apuntesuniovi.models.Subject
import es.uniovi.apuntesuniovi.repositories.interfaces.PagingQueryDslRepository

/**
 * Manage the Subject table
 */
interface SubjectRepository : PagingQueryDslRepository<Subject> {
    /**
     * Count by active
     */
    fun countByActive(active: Boolean): Int
}