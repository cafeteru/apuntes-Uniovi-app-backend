package es.uniovi.apuntesuniovi.repositories

import es.uniovi.apuntesuniovi.models.Subject

/**
 * Manage the Subject table
 */
interface SubjectRepository : PagingQueryDslRepository<Subject>