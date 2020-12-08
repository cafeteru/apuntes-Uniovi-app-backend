package es.uniovi.apuntesuniovi.repositories

import es.uniovi.apuntesuniovi.models.Semester
import org.springframework.data.repository.PagingAndSortingRepository

/**
 * Manage the Semester table
 */
interface SemesterRepository : PagingAndSortingRepository<Semester, Long>