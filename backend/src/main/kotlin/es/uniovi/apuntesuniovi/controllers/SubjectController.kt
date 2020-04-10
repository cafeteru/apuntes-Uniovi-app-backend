package es.uniovi.apuntesuniovi.controllers

import es.uniovi.apuntesuniovi.servicies.dtos.entities.SubjectDto
import org.springframework.http.ResponseEntity
import java.security.Principal

interface SubjectController {
    fun findAll(principal: Principal?): ResponseEntity<List<SubjectDto>>
    fun save(principal: Principal?, json: String?): ResponseEntity<List<SubjectDto>>
}