package es.uniovi.apuntesuniovi.servicies

import es.uniovi.apuntesuniovi.entities.Subject

interface SubjectService {
    fun findAll(): List<Subject>
    fun save(subject: Subject): List<Subject>
}