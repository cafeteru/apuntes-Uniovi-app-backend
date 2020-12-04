package es.uniovi.apuntesuniovi.servicies.dtos.impl

import es.uniovi.apuntesuniovi.entities.Center
import es.uniovi.apuntesuniovi.servicies.dtos.AbstractDtoAssembler
import es.uniovi.apuntesuniovi.servicies.dtos.entities.CenterDto
import org.springframework.stereotype.Service

@Service
class UniversityCenterDtoAssembler : AbstractDtoAssembler<Center, CenterDto>() {
    override fun entityToDto(entity: Center?): CenterDto {
        logService.info("entityToDto(entity: ${entity}) - start")
        entity?.let {
            val result = CenterDto(
                    id = it.id,
                    name = it.name,
                    address = it.address)
            logService.info("entityToDto(entity: ${entity}) - end")
            return result
        }
        logService.error("entityToDto(entity: ${entity}) - error")
        throw IllegalArgumentException()
    }

    override fun dtoToEntity(dto: CenterDto?): Center {
        logService.info("dtoToEntity(dto: ${dto}) - start")
        dto?.let {
            val result = Center()
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