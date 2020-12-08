package es.uniovi.apuntesuniovi.services

import es.uniovi.apuntesuniovi.infrastructure.log.LogService
import es.uniovi.apuntesuniovi.services.dtos.assemblers.AbstractAssembler
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Abstract service to define
 */
abstract class BaseService<Entity, Dto>(
    private val repository: JpaRepository<Entity, Long>,
    private val assembler: AbstractAssembler<Entity, Dto>
) {
    protected val logService = LogService(this.javaClass)

    /**
     * Create a new element
     *
     * @param dto Element to create
     */
    fun create(dto: Dto): List<Dto> {
        logService.info("create(dto: UserDto) - start")
        val entity = assembler.dtoToEntity(dto)
        val result = create(repository, entity)
        logService.info("create(dto: UserDto) - end")
        return assembler.listToDto(result)
    }

    protected abstract fun create(repository: JpaRepository<Entity, Long>, entity: Entity): List<Entity>

    /**
     * Returns all elements
     */
    fun findAll(): List<Dto> {
        logService.info("findAll() - start")
        val result = findAll(repository)
        logService.info("findAll() - end")
        return assembler.listToDto(result)
    }

    protected abstract fun findAll(repository: JpaRepository<Entity, Long>): List<Entity>
}