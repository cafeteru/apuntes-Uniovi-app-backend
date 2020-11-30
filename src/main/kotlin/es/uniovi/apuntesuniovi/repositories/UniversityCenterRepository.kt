package es.uniovi.apuntesuniovi.repositories

import es.uniovi.apuntesuniovi.entities.UniversityCenter
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Manage the UniversityCenter table
 */
interface UniversityCenterRepository : JpaRepository<UniversityCenter, Long>