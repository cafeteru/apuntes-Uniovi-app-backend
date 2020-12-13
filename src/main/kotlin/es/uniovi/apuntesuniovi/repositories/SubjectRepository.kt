package es.uniovi.apuntesuniovi.repositories

import es.uniovi.apuntesuniovi.models.Subject
import org.springframework.data.repository.PagingAndSortingRepository

/**
 * Manage the subjects table
 */
interface SubjectRepository : PagingAndSortingRepository<Subject, Long>