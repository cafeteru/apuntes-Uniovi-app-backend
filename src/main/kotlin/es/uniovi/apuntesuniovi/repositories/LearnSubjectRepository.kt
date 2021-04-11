package es.uniovi.apuntesuniovi.repositories

import es.uniovi.apuntesuniovi.models.LearnSubject
import es.uniovi.apuntesuniovi.repositories.interfaces.PagingQueryDslRepository

/**
 * Manage the LearnSubject table
 */
interface LearnSubjectRepository : PagingQueryDslRepository<LearnSubject> {

    fun findBySubjectId(id: Long): List<LearnSubject>
}