package es.uniovi.apuntesuniovi.repositories.builders

import com.querydsl.core.BooleanBuilder

interface Builder<Dto> {

    /**
     * Create conditions to filter
     */
    fun createBuilder(dto: Dto?): BooleanBuilder
}