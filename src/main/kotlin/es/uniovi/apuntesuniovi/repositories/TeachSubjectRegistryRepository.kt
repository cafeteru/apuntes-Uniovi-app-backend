package es.uniovi.apuntesuniovi.repositories

import es.uniovi.apuntesuniovi.entities.TeachSubjectRegistry
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Manage the TeachSubjectRegistry table
 */
interface TeachSubjectRegistryRepository : JpaRepository<TeachSubjectRegistry, Long>