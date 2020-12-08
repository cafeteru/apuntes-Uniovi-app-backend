package es.uniovi.apuntesuniovi.repositories

import es.uniovi.apuntesuniovi.models.Course
import org.springframework.data.repository.PagingAndSortingRepository

/**
 * Manage the Course table
 */
interface CourseRepository : PagingAndSortingRepository<Course, Long>