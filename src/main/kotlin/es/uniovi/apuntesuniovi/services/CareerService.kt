package es.uniovi.apuntesuniovi.services

import es.uniovi.apuntesuniovi.models.Center
import es.uniovi.apuntesuniovi.models.ConfigurationECTS
import es.uniovi.apuntesuniovi.infrastructure.log.LogService
import es.uniovi.apuntesuniovi.repositories.CareerRepository
import es.uniovi.apuntesuniovi.repositories.CenterRepository
import es.uniovi.apuntesuniovi.repositories.ConfigurationECTSRepository
import es.uniovi.apuntesuniovi.services.commands.careers.CreateCareerService
import es.uniovi.apuntesuniovi.services.commands.careers.FindAllCareersService
import es.uniovi.apuntesuniovi.services.commands.centers.FindCenterByIdService
import es.uniovi.apuntesuniovi.services.commands.configurationECTS.FindConfigurationECTSByIdService
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
    private val centerRepository: CenterRepository,
    private val configurationECTSRepository: ConfigurationECTSRepository,
    private val careerAssembler: CareerAssembler
) {
    private val logService = LogService(this.javaClass)

    /**
     * Create a career
     *
     * @param careerDto Career to save
     */
    fun create(careerDto: CareerDto): List<CareerDto> {
        logService.info("create(careerDto: CareerDto) - start")
        val career = careerAssembler.dtoToEntity(careerDto)
        var center: Center? = null
        careerDto.centerId?.let {
            center = FindCenterByIdService(centerRepository, it).execute()[0]
        }
        var configurationECTS: ConfigurationECTS? = null
        careerDto.configurationECTSId?.let {
            configurationECTS = FindConfigurationECTSByIdService(
                configurationECTSRepository, it
            ).execute()[0]
        }
        val result = CreateCareerService(careerRepository, career, center, configurationECTS).execute()
        logService.info("create(careerDto: CareerDto) - end")
        return careerAssembler.listToDto(result)
    }

    /**
     * Returns all careers
     */
    fun findAll(): List<CareerDto> {
        logService.info("findAll() - start")
        val result = FindAllCareersService(careerRepository).execute()
        logService.info("findAll() - end")
        return careerAssembler.listToDto(result)
    }
}