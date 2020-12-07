package es.uniovi.apuntesuniovi.repositories

import es.uniovi.apuntesuniovi.models.Course
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Manage the Course table
 */
interface CourseRepository : JpaRepository<Course, Long>