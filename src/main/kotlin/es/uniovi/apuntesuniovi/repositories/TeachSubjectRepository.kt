package es.uniovi.apuntesuniovi.repositories

import es.uniovi.apuntesuniovi.models.TeachSubject
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Manage the teachSubject table
 */
interface TeachSubjectRepository : JpaRepository<TeachSubject, Long>