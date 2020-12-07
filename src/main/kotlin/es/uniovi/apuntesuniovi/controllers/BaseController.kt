package es.uniovi.apuntesuniovi.controllers

import es.uniovi.apuntesuniovi.infrastructure.AbstractCommand
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
abstract class BaseController<Dto> constructor(
    private val baseService: BaseService<Dto>
) {
    private val logService = LogService(this.javaClass)

    /**
     * Add a new user through a text string (JSON)
     */
    @PostMapping("/create")
    fun create(@RequestBody json: String): ResponseEntity<List<Dto>> {
        logService.info("save(json: String) - start")
        val result = getCreateCommand(baseService, json).execute()
        logService.info("save(json: String) - end")
        return ResponseEntity(result, HttpStatus.OK)
    }

    protected abstract fun getCreateCommand(baseService: BaseService<Dto>, json: String): AbstractCommand<List<Dto>>

    /**
     * Returns all registered in the system
     */
    @GetMapping("")
    fun findAll(): ResponseEntity<List<Dto>> {
        logService.info("findAll() - start")
        val result = getFindAllCommand(baseService).execute()
        logService.info("findAll() - end")
        return ResponseEntity(result, HttpStatus.OK)
    }

    protected abstract fun getFindAllCommand(baseService: BaseService<Dto>): AbstractCommand<List<Dto>>
}