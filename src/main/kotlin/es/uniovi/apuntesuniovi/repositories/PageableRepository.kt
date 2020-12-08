package es.uniovi.apuntesuniovi.repositories

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.NoRepositoryBean

@NoRepositoryBean
interface PageableRepository<T> : JpaRepository<T, Long> {
    override fun findAll(pageable: Pageable): Page<T>
}