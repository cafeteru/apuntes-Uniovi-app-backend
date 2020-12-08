package es.uniovi.apuntesuniovi.repositories

import es.uniovi.apuntesuniovi.models.TeachSubjectRegistry
import org.springframework.data.repository.PagingAndSortingRepository

/**
 * Manage the TeachSubjectRegistry table
 */
interface TeachSubjectRegistryRepository : PagingAndSortingRepository<TeachSubjectRegistry, Long>