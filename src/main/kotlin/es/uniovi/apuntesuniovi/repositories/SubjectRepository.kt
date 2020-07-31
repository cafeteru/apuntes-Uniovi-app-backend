package es.uniovi.apuntesuniovi.repositories

import es.uniovi.apuntesuniovi.entities.Subject
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface SubjectRepository : JpaRepository<Subject, Long> {
    fun findByNameAndCourse(name: String, course: Int): Optional<Subject>
}