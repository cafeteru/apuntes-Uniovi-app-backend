package es.uniovi.apuntesuniovi.repositories

import es.uniovi.apuntesuniovi.entities.TeachSubject
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Manage the teachSubject table
 */
interface TeachSubjectRepository : JpaRepository<TeachSubject, Long>