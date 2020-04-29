package es.uniovi.apuntesuniovi.repositories

import es.uniovi.apuntesuniovi.entities.Student
import org.springframework.data.jpa.repository.JpaRepository

interface StudentRepository : JpaRepository<Student, Long>