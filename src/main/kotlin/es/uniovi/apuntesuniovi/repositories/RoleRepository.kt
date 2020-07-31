package es.uniovi.apuntesuniovi.repositories

import es.uniovi.apuntesuniovi.entities.Role
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Manage the roles table
 */
interface RoleRepository : JpaRepository<Role, Long>