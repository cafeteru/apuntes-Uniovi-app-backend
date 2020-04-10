package es.uniovi.apuntesuniovi.controllers

import es.uniovi.apuntesuniovi.entities.Subject
import org.springframework.http.ResponseEntity
import java.security.Principal

interface SubjectController {
    fun findAll(principal: Principal?): ResponseEntity<List<Subject>>
}