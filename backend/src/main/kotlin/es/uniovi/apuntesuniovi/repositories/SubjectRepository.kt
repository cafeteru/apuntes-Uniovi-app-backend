package es.uniovi.apuntesuniovi.repositories

import es.uniovi.apuntesuniovi.entities.Subject
import org.springframework.data.jpa.repository.JpaRepository

interface SubjectRepository : JpaRepository<Subject, Long>