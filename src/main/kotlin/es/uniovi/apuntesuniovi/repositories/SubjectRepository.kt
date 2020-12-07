package es.uniovi.apuntesuniovi.repositories

import es.uniovi.apuntesuniovi.models.Subject
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

/**
 * Manage the subjects table
 */
interface SubjectRepository : JpaRepository<Subject, Long> {
    /**
     * Find subject by name
     */
    fun findByName(name: String): Optional<Subject>
}