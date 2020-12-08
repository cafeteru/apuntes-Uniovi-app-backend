package es.uniovi.apuntesuniovi.repositories

import es.uniovi.apuntesuniovi.models.TeachSubject
import org.springframework.data.repository.PagingAndSortingRepository

/**
 * Manage the teachSubject table
 */
interface TeachSubjectRepository : PagingAndSortingRepository<TeachSubject, Long>