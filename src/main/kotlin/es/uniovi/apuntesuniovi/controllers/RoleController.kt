package es.uniovi.apuntesuniovi.controllers

import es.uniovi.apuntesuniovi.servicies.dtos.entities.RoleDto
import org.springframework.http.ResponseEntity

/**
 * Controlador de roles
 */
interface RoleController {
    /**
     * Devuelve todas los roles registrados en el sistema
     */
    fun findAll(): ResponseEntity<List<RoleDto>>

    /**
     * Añade un nuevo rol a través de una cadena de texto (JSON)
     */
    fun save(json: String?): ResponseEntity<List<RoleDto>>
}