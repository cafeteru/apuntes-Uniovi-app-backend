package es.uniovi.apuntesuniovi.servicies.commands.centers

import es.uniovi.apuntesuniovi.infrastructure.Command
import es.uniovi.apuntesuniovi.infrastructure.log.LogService
import es.uniovi.apuntesuniovi.repositories.CenterRepository
import es.uniovi.apuntesuniovi.servicies.dtos.entities.CenterDto
import es.uniovi.apuntesuniovi.servicies.dtos.impl.CenterDtoAssembler

/**
 * Return all centers since repository layer
 */
class FindAllCentersService(
    private val centerRepository: CenterRepository,
    private val centerDtoAssembler: CenterDtoAssembler
) : Command<List<CenterDto>> {
    private val logService = LogService(this.javaClass)

    override fun execute(): List<CenterDto> {
        logService.info("execute() - start")
        val list = centerRepository.findAll()
        val result = centerDtoAssembler.listToDto(list)
        logService.info("execute() - end")
        return result
    }
}