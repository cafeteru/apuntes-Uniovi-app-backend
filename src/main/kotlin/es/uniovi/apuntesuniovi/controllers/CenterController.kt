package es.uniovi.apuntesuniovi.controllers

import es.uniovi.apuntesuniovi.controllers.commands.centers.FindAllCenters
import es.uniovi.apuntesuniovi.controllers.commands.centers.CreateCenter
import es.uniovi.apuntesuniovi.infrastructure.log.LogService
import es.uniovi.apuntesuniovi.services.CenterService
import es.uniovi.apuntesuniovi.services.dtos.entities.CenterDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

/**
 * Define centers endpoints
 */
@RestController
@RequestMapping("/centers")
class CenterController @Autowired constructor(
    private val centerService: CenterService
) {
    private val logService = LogService(this.javaClass)

    /**
     * Add a new center through a text string (JSON)
     */
    @PostMapping("/create")
    fun create(@RequestBody json: String): ResponseEntity<List<CenterDto>> {
        logService.info("save(json: String) - start")
        val result = CreateCenter(centerService, json).execute()
        logService.info("save(json: String) - end")
        return ResponseEntity(result, HttpStatus.OK)
    }

    /**
     * Returns all centers registered in the system
     */
    @GetMapping("")
    fun findAll(): ResponseEntity<List<CenterDto>> {
        logService.info("findAll() - start")
        val result = FindAllCenters(centerService).execute()
        logService.info("findAll() - end")
        return ResponseEntity(result, HttpStatus.OK)
    }
}