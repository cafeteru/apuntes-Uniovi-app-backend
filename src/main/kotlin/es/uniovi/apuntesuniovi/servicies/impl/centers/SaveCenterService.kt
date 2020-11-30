package es.uniovi.apuntesuniovi.servicies.impl.centers

import es.uniovi.apuntesuniovi.infrastructure.Command
import es.uniovi.apuntesuniovi.infrastructure.log.LogService
import es.uniovi.apuntesuniovi.repositories.CenterRepository
import es.uniovi.apuntesuniovi.servicies.dtos.entities.UniversityCenterDto
import es.uniovi.apuntesuniovi.servicies.dtos.impl.UniversityCenterDtoAssembler
import java.util.*

class SaveCenterService(
        private val universityCenterRepository: CenterRepository,
        private val universityCenterDtoAssembler: UniversityCenterDtoAssembler,
        private val universityCenterDto: UniversityCenterDto
) : Command<List<UniversityCenterDto>> {
    private val logService = LogService(this.javaClass)

    override fun execute(): List<UniversityCenterDto> {
        logService.info("execute() - start")
        val subject = universityCenterDtoAssembler.dtoToEntity(universityCenterDto)
        val result = universityCenterRepository.save(subject)
        val list = ArrayList<UniversityCenterDto>()
        list.add(universityCenterDtoAssembler.entityToDto(result))
        logService.info("execute() - end")
        return list
    }
}