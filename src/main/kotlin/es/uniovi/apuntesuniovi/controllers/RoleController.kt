package es.uniovi.apuntesuniovi.controllers

import es.uniovi.apuntesuniovi.servicies.dtos.entities.RoleDto
import org.springframework.http.ResponseEntity

/**
 * Define role endpoints
 */
interface RoleController {
    /**
     * Returns all the roles registered in the system
     */
    fun findAll(): ResponseEntity<List<RoleDto>>

    /**
     * Add a new role through a text string (JSON)
     */
    fun save(json: String): ResponseEntity<List<RoleDto>>
}