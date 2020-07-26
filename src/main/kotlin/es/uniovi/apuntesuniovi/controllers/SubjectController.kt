package es.uniovi.apuntesuniovi.controllers

import es.uniovi.apuntesuniovi.servicies.dtos.entities.SubjectDto
import org.springframework.http.ResponseEntity
import java.security.Principal

/**
 * Controlador de las asignaturas
 */
interface SubjectController : Controller {
    /**
     * Devuelve todas las asignaturas registradas en el sistema
     */
    fun findAll(principal: Principal?): ResponseEntity<List<SubjectDto>>

    /**
     * Añade una nueva asignatura a través de una cadena de texto (JSON)
     */
    fun save(principal: Principal?, json: String?): ResponseEntity<List<SubjectDto>>
}