package es.uniovi.apuntesuniovi.repositories

import es.uniovi.apuntesuniovi.models.LearnSubject
import es.uniovi.apuntesuniovi.repositories.interfaces.PagingQueryDslRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

/**
 * Manage the LearnSubject table
 */
interface LearnSubjectRepository : PagingQueryDslRepository<LearnSubject> {

    fun findBySubjectId(id: Long, pageable: Pageable): Page<LearnSubject>
}