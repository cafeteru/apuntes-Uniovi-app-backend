package es.uniovi.apuntesuniovi.services

import es.uniovi.apuntesuniovi.repositories.CareerRepository
import es.uniovi.apuntesuniovi.services.commands.careers.CreateCareerService
import es.uniovi.apuntesuniovi.services.commands.careers.FindAllCareersService
import es.uniovi.apuntesuniovi.services.dtos.assemblers.CareerAssembler
import es.uniovi.apuntesuniovi.services.dtos.entities.CareerDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Service to manage careers
 */
@Service
class CareerService @Autowired constructor(
    private val careerRepository: CareerRepository,
    private val careerAssembler: CareerAssembler
) : AbstractService<CareerDto>() {

    override fun create(dto: CareerDto): List<CareerDto> {
        logService.info("create(dto: CareerDto) - start")
        val career = careerAssembler.dtoToEntity(dto)
        val result = CreateCareerService(careerRepository, career).execute()
        logService.info("create(dto: CareerDto) - end")
        return careerAssembler.listToDto(result)
    }

    override fun findAll(): List<CareerDto> {
        logService.info("findAll() - start")
        val result = FindAllCareersService(careerRepository).execute()
        logService.info("findAll() - end")
        return careerAssembler.listToDto(result)
    }
}