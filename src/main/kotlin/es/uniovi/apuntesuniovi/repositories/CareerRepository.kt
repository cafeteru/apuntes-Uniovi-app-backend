package es.uniovi.apuntesuniovi.repositories

import es.uniovi.apuntesuniovi.models.Career
import org.springframework.data.repository.PagingAndSortingRepository

/**
 * Manage the Career table
 */
interface CareerRepository : PagingAndSortingRepository<Career, Long>