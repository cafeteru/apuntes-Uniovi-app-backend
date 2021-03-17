package es.uniovi.apuntesuniovi.services.dtos.assemblers

import es.uniovi.apuntesuniovi.infrastructure.messages.CareerMessages
import es.uniovi.apuntesuniovi.models.Career
import es.uniovi.apuntesuniovi.services.dtos.entities.CareerDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.util.Assert

/**
 * Define the entity and dto conversion methods of careers
 */
@Service
class CareerAssembler @Autowired constructor(
) : AbstractAssembler<Career, CareerDto>() {
  override fun entityToDto(entity: Career?): CareerDto {
    logService.info("entityToDto(entity: Career) - start")
    Assert.notNull(entity, CareerMessages.NULL)
    entity?.let {
      val dto = CareerDto(
        id = it.id,
        name = it.name,
        code = it.code,
        yearImplantation = it.yearImplantation,
        etcs = it.etcs,
        languages = it.languages.map { language -> language.toString() },
      )
      logService.info("entityToDto(entity: Career) - end")
      return dto
    }
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
      entity.etcs = it.etcs
      it.languages.forEach { language -> entity.addLanguage(language) }
      logService.info("dtoToEntity(dto: CareerDto) - end")
      return entity
    }
    throw IllegalArgumentException(CareerMessages.NULL)
  }
}