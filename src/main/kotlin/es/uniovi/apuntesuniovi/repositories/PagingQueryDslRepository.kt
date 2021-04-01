package es.uniovi.apuntesuniovi.repositories

import org.springframework.data.querydsl.QuerydslPredicateExecutor
import org.springframework.data.repository.NoRepositoryBean
import org.springframework.data.repository.PagingAndSortingRepository

@NoRepositoryBean
interface PagingQueryDslRepository<Entity> : PagingAndSortingRepository<Entity, Long>,
    QuerydslPredicateExecutor<Entity>