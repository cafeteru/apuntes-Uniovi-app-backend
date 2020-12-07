package es.uniovi.apuntesuniovi.services.commands.careers

import es.uniovi.apuntesuniovi.infrastructure.AbstractCommand
import es.uniovi.apuntesuniovi.models.Career
import es.uniovi.apuntesuniovi.repositories.CareerRepository

/**
 * Create a career in service layer
 */
class CreateCareerService(
    private val careerRepository: CareerRepository,
    private val career: Career,
) : AbstractCommand<List<Career>>() {
    override fun execute(): List<Career> {
        logService.info("execute() - start")
        val result = careerRepository.save(career)
        logService.info("execute() - end")
        return listOf(result)
    }
}