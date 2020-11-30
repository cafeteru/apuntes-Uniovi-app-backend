package es.uniovi.apuntesuniovi.servicies.dtos.impl

import es.uniovi.apuntesuniovi.entities.UniversityCenter
import es.uniovi.apuntesuniovi.servicies.dtos.AbstractDtoAssembler
import es.uniovi.apuntesuniovi.servicies.dtos.entities.UniversityCenterDto
import org.springframework.stereotype.Service

@Service
class UniversityCenterDtoAssembler : AbstractDtoAssembler<UniversityCenter, UniversityCenterDto>() {
    override fun entityToDto(entity: UniversityCenter?): UniversityCenterDto {
        logService.info("entityToDto(entity: ${entity}) - start")
        entity?.let {
            val result = UniversityCenterDto(
                    id = it.id,
                    name = it.name,
                    address = it.address)
            logService.info("entityToDto(entity: ${entity}) - end")
            return result
        }
        logService.error("entityToDto(entity: ${entity}) - error")
        throw IllegalArgumentException()
    }

    override fun dtoToEntity(dto: UniversityCenterDto?): UniversityCenter {
        logService.info("dtoToEntity(dto: ${dto}) - start")
        dto?.let {
            val result = UniversityCenter()
            it.id?.let { id ->
                result.id = id
            }
            result.name = it.name
            result.address = it.address
            logService.info("dtoToEntity(dto: ${dto}) - end")
            return result
        }
        logService.error("dtoToEntity(dto: ${dto}) - error")
        throw IllegalArgumentException()
    }
}