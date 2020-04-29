package es.uniovi.apuntesuniovi.controllers

import es.uniovi.apuntesuniovi.entities.Student
import org.springframework.http.ResponseEntity
import java.security.Principal

/**
 * Controlador de las personas
 */
interface StudentController {
    fun findAll(principal: Principal?): ResponseEntity<List<Student>>
}