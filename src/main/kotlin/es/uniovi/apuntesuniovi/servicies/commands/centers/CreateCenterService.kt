package es.uniovi.apuntesuniovi.servicies.commands.centers

import es.uniovi.apuntesuniovi.infrastructure.Command
import es.uniovi.apuntesuniovi.infrastructure.log.LogService
import es.uniovi.apuntesuniovi.repositories.CenterRepository
import es.uniovi.apuntesuniovi.servicies.dtos.entities.CenterDto
import es.uniovi.apuntesuniovi.servicies.dtos.impl.CenterDtoAssembler
import java.util.*

/**
 * Create a center in service layer
 */
class CreateCenterService(
    private val centerRepository: CenterRepository,
    private val centerDtoAssembler: CenterDtoAssembler,
    private val centerDto: CenterDto
) : Command<List<CenterDto>> {
    private val logService = LogService(this.javaClass)

    override fun execute(): List<CenterDto> {
        logService.info("execute() - start")
        val subject = centerDtoAssembler.dtoToEntity(centerDto)
        val result = centerRepository.save(subject)
        val list = ArrayList<CenterDto>()
        list.add(centerDtoAssembler.entityToDto(result))
        logService.info("execute() - end")
        return list
    }
}