package es.uniovi.apuntesuniovi.services.commands

import es.uniovi.apuntesuniovi.dtos.entities.UserDto
import es.uniovi.apuntesuniovi.infrastructure.AbstractCommand
import es.uniovi.apuntesuniovi.repositories.PagingQueryDslRepository
import es.uniovi.apuntesuniovi.repositories.builders.Builder
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

/**
 * Return all entities in service layer
 */
abstract class BaseFindAllCommand<Entity, Dto>(
    private val repository: PagingQueryDslRepository<Entity>,
    private val builder: Builder<Dto>,
    private val dto: Dto?,
    private val pageable: Pageable
) : AbstractCommand<Page<Entity>>() {

    override fun execute(): Page<Entity> {
        logService.info("execute() - start")
        val filters = builder.createBuilder(dto)
        val list = repository.findAll(filters, pageable)
        logService.info("execute() - end")
        return list
    }
}