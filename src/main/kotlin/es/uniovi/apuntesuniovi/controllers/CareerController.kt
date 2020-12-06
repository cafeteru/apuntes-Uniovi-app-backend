package es.uniovi.apuntesuniovi.controllers

import es.uniovi.apuntesuniovi.controllers.commands.careers.CreateCareer
import es.uniovi.apuntesuniovi.controllers.commands.careers.FindAllCareers
import es.uniovi.apuntesuniovi.controllers.commands.centers.FindAllCenters
import es.uniovi.apuntesuniovi.controllers.commands.centers.CreateCenter
import es.uniovi.apuntesuniovi.infrastructure.log.LogService
import es.uniovi.apuntesuniovi.servicies.CareerService
import es.uniovi.apuntesuniovi.servicies.CenterService
import es.uniovi.apuntesuniovi.servicies.dtos.entities.CareerDto
import es.uniovi.apuntesuniovi.servicies.dtos.entities.CenterDto
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
        logService.info("save(json: ${logService.formatJson(json)}) - start")
        val result = CreateCareer(careerService, json).execute()
        logService.info("save(json:${logService.formatJson(json)}) - end")
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