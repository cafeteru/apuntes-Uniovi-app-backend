package es.uniovi.apuntesuniovi.controllers

import es.uniovi.apuntesuniovi.servicies.dtos.entities.UserDto
import org.springframework.http.ResponseEntity

/**
 * Define user endpoints
 */
interface UserController {
    /**
     * Returns all registered users in the system
     */
    fun findAll(): ResponseEntity<List<UserDto>>

    /**
     * Add a new user through a text string (JSON)
     */
    fun save(json: String): ResponseEntity<List<UserDto>>
}