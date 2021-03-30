package es.uniovi.apuntesuniovi.controllers

import es.uniovi.apuntesuniovi.infrastructure.log.LogService
import es.uniovi.apuntesuniovi.services.BaseService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import javax.validation.Valid

/**
 * Define base endpoints
 */
abstract class BaseController<Entity, Dto> constructor(
    private val baseService: BaseService<Entity, Dto>
) {
    private val logService = LogService(this.javaClass)

    /**
     * Add a new entity through a text string (JSON)
     */
    @PostMapping("/create")
    fun create(@Valid @RequestBody dto: Dto): ResponseEntity<Dto> {
        logService.info("save(json: String) - start")
        val result = create(baseService, dto)
        logService.info("save(json: String) - end")
        return ResponseEntity(result, HttpStatus.OK)
    }

    /**
     * Return the controller command to execute create
     */
    protected abstract fun create(baseService: BaseService<Entity, Dto>, dto: Dto): Dto

    /**
     * Returns all registered in the system
     */
    @GetMapping("")
    fun findAll(pageable: Pageable): ResponseEntity<Page<Dto>> {
        logService.info("findAll() - start")
        val result = findAll(baseService, pageable)
        logService.info("findAll() - end")
        return ResponseEntity(result, HttpStatus.OK)
    }

    /**
     * Return the controller command to execute findAll
     */
    protected abstract fun findAll(
        baseService: BaseService<Entity, Dto>,
        pageable: Pageable
    ): Page<Dto>
}