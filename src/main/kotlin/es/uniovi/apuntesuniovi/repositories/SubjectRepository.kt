package es.uniovi.apuntesuniovi.repositories

import es.uniovi.apuntesuniovi.entities.Subject
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

/**
 * Manage the subjects table
 */
interface SubjectRepository : JpaRepository<Subject, Long> {
    /**
     * Find subject by name and course
     */
    fun findByNameAndCourse(name: String, course: Int): Optional<Subject>
}