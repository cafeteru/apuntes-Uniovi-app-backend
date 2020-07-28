package es.uniovi.apuntesuniovi.controllers

import es.uniovi.apuntesuniovi.servicies.dtos.entities.SubjectDto
import es.uniovi.apuntesuniovi.servicies.dtos.entities.UserDto
import org.springframework.http.ResponseEntity

/**
 * Controlador de usuarios
 */
interface UserController {
    /**
     * Devuelve todos los usuarios registrados en el sistema
     */
    fun findAll(): ResponseEntity<List<UserDto>>

    /**
     * Añade un nuevo usuario a través de una cadena de texto (JSON)
     */
    fun save(json: String?): ResponseEntity<List<UserDto>>
}