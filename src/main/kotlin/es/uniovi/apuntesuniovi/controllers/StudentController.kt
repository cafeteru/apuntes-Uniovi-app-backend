package es.uniovi.apuntesuniovi.controllers

import es.uniovi.apuntesuniovi.entities.Student
import org.springframework.http.ResponseEntity

/**
 * Controlador de las personas
 */
interface StudentController {
    fun findAll(): ResponseEntity<List<Student>>
}