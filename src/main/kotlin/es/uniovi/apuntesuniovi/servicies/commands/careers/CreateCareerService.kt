package es.uniovi.apuntesuniovi.servicies.commands.careers

import es.uniovi.apuntesuniovi.entities.Career
import es.uniovi.apuntesuniovi.entities.Center
import es.uniovi.apuntesuniovi.entities.ConfigurationECTS
import es.uniovi.apuntesuniovi.infrastructure.AbstractCommand
import es.uniovi.apuntesuniovi.repositories.CareerRepository

/**
 * Create a career in service layer
 */
class CreateCareerService(
    private val careerRepository: CareerRepository,
    private val career: Career,
    private val center: Center?,
    private val configurationECTS: ConfigurationECTS?
) : AbstractCommand<List<Career>>() {
    override fun execute(): List<Career> {
        logService.info("execute() - start")
//        career.center = center
//        career.configurationECTS = configurationECTS
        val result = careerRepository.save(career)
        logService.info("execute() - end")
        return listOf(result)
    }
}