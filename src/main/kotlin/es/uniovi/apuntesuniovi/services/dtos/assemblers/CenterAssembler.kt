package es.uniovi.apuntesuniovi.services.dtos.assemblers

import es.uniovi.apuntesuniovi.models.Center
import es.uniovi.apuntesuniovi.services.dtos.entities.CenterDto
import org.springframework.stereotype.Service

/**
 * Define the entity and dto conversion methods of centers
 */
@Service
class CenterAssembler : AbstractAssembler<Center, CenterDto>() {
    override fun entityToDto(entity: Center?): CenterDto {
        logService.info("entityToDto(entity: ${entity}) - start")
        entity?.let {
            val result = CenterDto(
                id = it.id,
                name = it.name,
                address = it.address
            )
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
            result.id = it.id
            result.name = it.name
            result.address = it.address
            logService.info("dtoToEntity(dto: ${dto}) - end")
            return result
        }
        logService.error("dtoToEntity(dto: ${dto}) - error")
        throw IllegalArgumentException()
    }
}