package es.uniovi.apuntesuniovi.services

import es.uniovi.apuntesuniovi.infrastructure.log.LogService

/**
 * Abstract service to define
 */
abstract class BaseService<Dto> {
    protected val logService = LogService(this.javaClass)

    /**
     * Create a new element
     *
     * @param dto Element to create
     */
    abstract fun create(dto: Dto): List<Dto>

    /**
     * Returns all elements
     */
    abstract fun findAll(): List<Dto>
}