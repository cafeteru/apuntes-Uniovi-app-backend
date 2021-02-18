package es.uniovi.apuntesuniovi.services.dtos.assemblers

import es.uniovi.apuntesuniovi.infrastructure.messages.CourseMessages
import es.uniovi.apuntesuniovi.models.Course
import es.uniovi.apuntesuniovi.repositories.CareerRepository
import es.uniovi.apuntesuniovi.services.commands.careers.FindCareerById
import es.uniovi.apuntesuniovi.services.dtos.entities.CourseDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Define the entity and dto conversion methods of careers
 */
@Service
class CourseAssembler @Autowired constructor(
  private val careerRepository: CareerRepository
) : AbstractAssembler<Course, CourseDto>() {
  override fun entityToDto(entity: Course?): CourseDto {
    logService.info("entityToDto(entity: Course) - start")
    entity?.let {
      val dto = CourseDto(
        id = it.id,
        position = it.position,
        careerId = it.career?.id
      )
      logService.info("entityToDto(entity: Course) - end")
      return dto
    }
    throw IllegalArgumentException(CourseMessages.NULL)
  }

  override fun dtoToEntity(dto: CourseDto?): Course {
    logService.info("dtoToEntity(dto: CourseDto) - start")
    dto?.let {
      val entity = Course()
      entity.id = it.id
      entity.position = it.position
      it.careerId?.let { id ->
        entity.career = FindCareerById(careerRepository, id).execute()
      }
      logService.info("dtoToEntity(dto: CourseDto) - end")
      return entity
    }
    throw IllegalArgumentException(CourseMessages.NULL)
  }
}