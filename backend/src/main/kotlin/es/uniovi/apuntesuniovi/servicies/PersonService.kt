package es.uniovi.apuntesuniovi.servicies

import es.uniovi.apuntesuniovi.servicies.dtos.entities.PersonDto

interface PersonService {
    fun findByUsername(username: String): PersonDto
}