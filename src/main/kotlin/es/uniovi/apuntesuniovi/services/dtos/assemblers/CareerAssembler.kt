package es.uniovi.apuntesuniovi.services.dtos.assemblers

import es.uniovi.apuntesuniovi.infrastructure.messages.CareerMessages
import es.uniovi.apuntesuniovi.models.Career
import es.uniovi.apuntesuniovi.repositories.CenterRepository
import es.uniovi.apuntesuniovi.services.commands.centers.FindCenterByIdService
import es.uniovi.apuntesuniovi.services.dtos.entities.CareerDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Define the entity and dto conversion methods of careers
 */
@Service
class CareerAssembler @Autowired constructor(
    private val centerRepository: CenterRepository
) : AbstractAssembler<Career, CareerDto>() {
    override fun entityToDto(entity: Career?): CareerDto {
        logService.info("entityToDto(entity: Career) - start")
        entity?.let {
            val dto = CareerDto(
                id = it.id,
                name = it.name,
                code = it.code,
                yearImplantation = it.yearImplantation,
                ECTS = it.etcs,
                languages = it.languages.map { language -> language.toString() },
                centerId = it.center?.id,
            )
            logService.info("entityToDto(entity: Career) - end")
            return dto
        }
        logService.error("entityToDto(entity: Career) - error")
        throw IllegalArgumentException(CareerMessages.NULL)
    }

    override fun dtoToEntity(dto: CareerDto?): Career {
        logService.info("dtoToEntity(dto: CareerDto) - start")
        dto?.let {
            val entity = Career()
            entity.id = it.id
            entity.name = it.name
            entity.code = it.code
            entity.yearImplantation = it.yearImplantation
            entity.etcs = it.ECTS
            it.languages.forEach { language -> entity.addLanguage(language) }
            it.centerId?.let { id ->
                entity.center = FindCenterByIdService(centerRepository, id).execute()[0]
            }
            logService.info("dtoToEntity(dto: CareerDto) - end")
            return entity
        }
        logService.info("dtoToEntity(dto: CareerDto) - error")
        throw IllegalArgumentException(CareerMessages.NULL)
    }
}