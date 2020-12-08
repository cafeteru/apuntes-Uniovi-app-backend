package es.uniovi.apuntesuniovi.repositories

import es.uniovi.apuntesuniovi.models.Subject
import java.util.*

/**
 * Manage the subjects table
 */
interface SubjectRepository : PageableRepository<Subject> {
    /**
     * Find subject by name
     */
    fun findByName(name: String): Optional<Subject>
}