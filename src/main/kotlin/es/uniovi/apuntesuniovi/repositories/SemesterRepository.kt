package es.uniovi.apuntesuniovi.repositories

import es.uniovi.apuntesuniovi.models.Semester
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Manage the Semester table
 */
interface SemesterRepository : JpaRepository<Semester, Long>