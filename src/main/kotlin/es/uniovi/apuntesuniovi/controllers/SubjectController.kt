package es.uniovi.apuntesuniovi.controllers

import es.uniovi.apuntesuniovi.servicies.dtos.entities.SubjectDto
import org.springframework.http.ResponseEntity

/**
 * Define subject endpoints
 */
interface SubjectController {
    /**
     * Returns all subjects registered in the system
     */
    fun findAll(): ResponseEntity<List<SubjectDto>>

    /**
     * Add a new subject through a text string (JSON)
     */
    fun save(json: String): ResponseEntity<List<SubjectDto>>
}