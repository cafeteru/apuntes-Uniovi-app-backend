package es.uniovi.apuntesuniovi.repositories

import es.uniovi.apuntesuniovi.models.Subject
import org.springframework.data.querydsl.QuerydslPredicateExecutor
import org.springframework.data.repository.PagingAndSortingRepository

/**
 * Manage the Subject table
 */
interface SubjectRepository : PagingAndSortingRepository<Subject, Long>,
    QuerydslPredicateExecutor<Subject>