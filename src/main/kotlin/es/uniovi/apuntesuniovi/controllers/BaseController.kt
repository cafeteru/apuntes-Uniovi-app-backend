package es.uniovi.apuntesuniovi.controllers

import es.uniovi.apuntesuniovi.infrastructure.log.LogService
import es.uniovi.apuntesuniovi.services.BaseService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

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
    fun create(@RequestBody json: String): ResponseEntity<List<Dto>> {
        logService.info("save(json: String) - start")
        val result = create(baseService, json)
        logService.info("save(json: String) - end")
        return ResponseEntity(result, HttpStatus.OK)
    }

    protected abstract fun create(baseService: BaseService<Entity, Dto>, json: String): List<Dto>

    /**
     * Returns all registered in the system
     */
    @GetMapping("")
    fun findAll(): ResponseEntity<List<Dto>> {
        logService.info("findAll() - start")
        val result = findAll(baseService)
        logService.info("findAll() - end")
        return ResponseEntity(result, HttpStatus.OK)
    }

    protected abstract fun findAll(baseService: BaseService<Entity, Dto>): List<Dto>
}