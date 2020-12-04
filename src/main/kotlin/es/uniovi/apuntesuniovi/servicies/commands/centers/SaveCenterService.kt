package es.uniovi.apuntesuniovi.servicies.commands.centers

import es.uniovi.apuntesuniovi.infrastructure.Command
import es.uniovi.apuntesuniovi.infrastructure.log.LogService
import es.uniovi.apuntesuniovi.repositories.CenterRepository
import es.uniovi.apuntesuniovi.servicies.dtos.entities.CenterDto
import es.uniovi.apuntesuniovi.servicies.dtos.impl.UniversityCenterDtoAssembler
import java.util.*

class SaveCenterService(
        private val universityCenterRepository: CenterRepository,
        private val universityCenterDtoAssembler: UniversityCenterDtoAssembler,
        private val universityCenterDto: CenterDto
) : Command<List<CenterDto>> {
    private val logService = LogService(this.javaClass)

    override fun execute(): List<CenterDto> {
        logService.info("execute() - start")
        val subject = universityCenterDtoAssembler.dtoToEntity(universityCenterDto)
        val result = universityCenterRepository.save(subject)
        val list = ArrayList<CenterDto>()
        list.add(universityCenterDtoAssembler.entityToDto(result))
        logService.info("execute() - end")
        return list
    }
}