package es.uniovi.apuntesuniovi.repositories

import es.uniovi.apuntesuniovi.entities.Person
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface PersonRepository : JpaRepository<Person, Long> {
    fun findByUsername(userName: String): Optional<Person>
}