package es.uniovi.apuntesuniovi.servicies.dtos.assemblers

import es.uniovi.apuntesuniovi.entities.Career
import es.uniovi.apuntesuniovi.infrastructure.messages.CareerMessages
import es.uniovi.apuntesuniovi.repositories.CenterRepository
import es.uniovi.apuntesuniovi.repositories.ConfigurationECTSRepository
import es.uniovi.apuntesuniovi.servicies.dtos.entities.CareerDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Define the entity and dto conversion methods of careers
 */
@Service
class CareerAssembler @Autowired constructor(
    private val centerRepository: CenterRepository,
    private val configurationECTSRepository: ConfigurationECTSRepository
) : AbstractDtoAssembler<Career, CareerDto>() {
    override fun entityToDto(entity: Career?): CareerDto {
        logService.info("entityToDto(entity: Career) - start")
        entity?.let {
            val dto = CareerDto(
                id = it.id,
                name = it.name,
                code = it.code,
                yearImplantation = it.yearImplantation,
                typeTeaching = it.typeTeaching,
                ISCED = it.ISCED,
                ECTS = it.ECTS,
                languages = it.languages,
                centerId = it.center?.id,
                configurationECTSId = it.configurationECTS?.id
            )
            logService.info("entityToDto(entity: Career) - end")
            return dto
        }
        logService.error("entityToDto(entity: Career) - error")
        throw IllegalArgumentException(CareerMessages.NULL_CAREER)
    }

    override fun dtoToEntity(dto: CareerDto?): Career {
        logService.info("dtoToEntity(dto: CareerDto) - start")
        dto?.let {
            val entity = Career()
            entity.id = it.id
            entity.name = it.name
            entity.code = it.code
            entity.yearImplantation = it.yearImplantation
            entity.typeTeaching = it.typeTeaching
            entity.ISCED = it.ISCED
            entity.ECTS = it.ECTS
            entity.languages = it.languages
            it.centerId?.let { id ->
                entity.center = centerRepository.getOne(id)
            }
            it.configurationECTSId?.let { id ->
                entity.configurationECTS = configurationECTSRepository.getOne(id)
            }
            logService.info("dtoToEntity(dto: CareerDto) - end")
            return entity
        }
        logService.info("dtoToEntity(dto: CareerDto) - error")
        throw IllegalArgumentException(CareerMessages.NULL_CAREER)
    }
}