package es.uniovi.apuntesuniovi.controllers

import es.uniovi.apuntesuniovi.controllers.commands.careers.CreateCareer
import es.uniovi.apuntesuniovi.controllers.commands.careers.FindAllCareers
import es.uniovi.apuntesuniovi.infrastructure.log.LogService
import es.uniovi.apuntesuniovi.services.CareerService
import es.uniovi.apuntesuniovi.services.dtos.entities.CareerDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

/**
 * Define careers endpoints
 */
@RestController
@RequestMapping("/careers")
class CareerController @Autowired constructor(
    private val careerService: CareerService
) {
    private val logService = LogService(this.javaClass)

    /**
     * Add a new career through a text string (JSON)
     */
    @PostMapping("/create")
    fun create(@RequestBody json: String): ResponseEntity<List<CareerDto>> {
        logService.info("save(json: String) - start")
        val result = CreateCareer(careerService, json).execute()
        logService.info("save(json: String) - end")
        return ResponseEntity(result, HttpStatus.OK)
    }

    /**
     * Returns all careers registered in the system
     */
    @GetMapping("")
    fun findAll(): ResponseEntity<List<CareerDto>> {
        logService.info("findAll() - start")
        val result = FindAllCareers(careerService).execute()
        logService.info("findAll() - end")
        return ResponseEntity(result, HttpStatus.OK)
    }
}