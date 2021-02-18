package es.uniovi.apuntesuniovi.services.commands

import es.uniovi.apuntesuniovi.infrastructure.AbstractCommand
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.PagingAndSortingRepository

/**
 * Return all entities in service layer
 */
abstract class BaseFindAllService<Entity>(
  private val repository: PagingAndSortingRepository<Entity, Long>,
  private val pageable: Pageable
) : AbstractCommand<Page<Entity>>() {

  override fun execute(): Page<Entity> {
    logService.info("execute() - start")
    val list = repository.findAll(pageable)
    logService.info("execute() - end")
    return list
  }
}