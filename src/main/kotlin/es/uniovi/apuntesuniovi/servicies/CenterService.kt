package es.uniovi.apuntesuniovi.servicies

import es.uniovi.apuntesuniovi.infrastructure.log.LogService
import es.uniovi.apuntesuniovi.repositories.CenterRepository
import es.uniovi.apuntesuniovi.servicies.commands.centers.CreateCenterService
import es.uniovi.apuntesuniovi.servicies.commands.centers.FindAllCentersService
import es.uniovi.apuntesuniovi.servicies.dtos.entities.CenterDto
import es.uniovi.apuntesuniovi.servicies.dtos.impl.CenterDtoAssembler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Service to manage university center
 */
@Service
class CenterService @Autowired constructor(
    private val centerRepository: CenterRepository,
    private val centerDtoAssembler: CenterDtoAssembler
) {
    private val logService = LogService(this.javaClass)

    /**
     * Create a center
     *
     * @param centerDto center to save
     */
    fun create(centerDto: CenterDto): List<CenterDto> {
        logService.info("create(centerDto: centerDto) - start")
        val center = centerDtoAssembler.dtoToEntity(centerDto)
        val result = CreateCenterService(centerRepository, center).execute()
        logService.info("create(centerDto: centerDto) - end")
        return centerDtoAssembler.listToDto(result)
    }

    /**
     * Returns all subjects
     */
    fun findAll(): List<CenterDto> {
        logService.info("findAll() - start")
        val result = FindAllCentersService(centerRepository).execute()
        logService.info("findAll() - end")
        return centerDtoAssembler.listToDto(result)
    }
}