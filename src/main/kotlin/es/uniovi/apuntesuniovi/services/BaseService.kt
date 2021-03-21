package es.uniovi.apuntesuniovi.services

import es.uniovi.apuntesuniovi.infrastructure.log.LogService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.PagingAndSortingRepository

/**
 * Abstract service to define
 */
abstract class BaseService<Entity>(
  private val repository: PagingAndSortingRepository<Entity, Long>,
) {
  protected val logService = LogService(this.javaClass)

  /**
   * Create a new element
   *
   * @param entity Element to create
   */
  fun create(entity: Entity): Entity {
    logService.info("create(dto: UserDto) - start")
    val result = create(repository, entity)
    logService.info("create(dto: UserDto) - end")
    return result
  }

  /**
   * Indicates the command that will create the element
   */
  protected abstract fun create(
    repository: PagingAndSortingRepository<Entity, Long>,
    entity: Entity
  ): Entity

  /**
   * Returns all elements
   */
  fun findAll(pageable: Pageable): Page<Entity> {
    logService.info("findAll() - start")
    val result = findAll(repository, pageable)
    logService.info("findAll() - end")
    return result
  }

  /**
   * Indicates the command that will returns all elements
   */
  protected abstract fun findAll(
    repository: PagingAndSortingRepository<Entity, Long>,
    pageable: Pageable
  ): Page<Entity>
}