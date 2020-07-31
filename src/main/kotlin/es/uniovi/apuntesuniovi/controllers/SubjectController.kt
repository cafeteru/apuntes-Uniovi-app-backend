package es.uniovi.apuntesuniovi.controllers

import es.uniovi.apuntesuniovi.servicies.dtos.entities.SubjectDto
import org.springframework.http.ResponseEntity

/**
 * Controlador de las asignaturas
 */
interface SubjectController {
    /**
     * Devuelve todas las asignaturas registradas en el sistema
     */
    fun findAll(): ResponseEntity<List<SubjectDto>>

    /**
     * Añade una nueva asignatura a través de una cadena de texto (JSON)
     */
    fun save(json: String): ResponseEntity<List<SubjectDto>>
}