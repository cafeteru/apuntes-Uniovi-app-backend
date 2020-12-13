package es.uniovi.apuntesuniovi.repositories

import es.uniovi.apuntesuniovi.models.Center
import org.springframework.data.repository.PagingAndSortingRepository

/**
 * Manage the Center table
 */
interface CenterRepository : PagingAndSortingRepository<Center, Long>