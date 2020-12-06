package es.uniovi.apuntesuniovi.repositories

import es.uniovi.apuntesuniovi.models.Center
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Manage the Center table
 */
interface CenterRepository : JpaRepository<Center, Long>