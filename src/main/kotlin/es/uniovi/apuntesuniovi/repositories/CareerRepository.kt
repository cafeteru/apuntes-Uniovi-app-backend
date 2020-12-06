package es.uniovi.apuntesuniovi.repositories

import es.uniovi.apuntesuniovi.entities.Career
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Manage the Career table
 */
interface CareerRepository : JpaRepository<Career, Long>