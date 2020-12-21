package es.uniovi.apuntesuniovi.repositories

import es.uniovi.apuntesuniovi.models.UnitSubject
import org.springframework.data.repository.PagingAndSortingRepository

/**
 * Manage the UnitSubject table
 */
interface UnitRepository : PagingAndSortingRepository<UnitSubject, Long>