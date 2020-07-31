package es.uniovi.apuntesuniovi.repositories

import es.uniovi.apuntesuniovi.entities.Role
import org.springframework.data.jpa.repository.JpaRepository

interface RoleRepository : JpaRepository<Role, Long>