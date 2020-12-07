package es.uniovi.apuntesuniovi.services

import es.uniovi.apuntesuniovi.repositories.CenterRepository
import es.uniovi.apuntesuniovi.services.commands.centers.CreateCenterService
import es.uniovi.apuntesuniovi.services.commands.centers.FindAllCentersService
import es.uniovi.apuntesuniovi.services.dtos.assemblers.CenterAssembler
import es.uniovi.apuntesuniovi.services.dtos.entities.CenterDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Service to manage university center
 */
@Service
class CenterService @Autowired constructor(
    private val centerRepository: CenterRepository,
    private val centerDtoAssembler: CenterAssembler
) : AbstractService<CenterDto>() {

    override fun create(dto: CenterDto): List<CenterDto> {
        logService.info("create(dto: centerDto) - start")
        val center = centerDtoAssembler.dtoToEntity(dto)
        val result = CreateCenterService(centerRepository, center).execute()
        logService.info("create(dto: centerDto) - end")
        return centerDtoAssembler.listToDto(result)
    }

    override fun findAll(): List<CenterDto> {
        logService.info("findAll() - start")
        val result = FindAllCentersService(centerRepository).execute()
        logService.info("findAll() - end")
        return centerDtoAssembler.listToDto(result)
    }
}