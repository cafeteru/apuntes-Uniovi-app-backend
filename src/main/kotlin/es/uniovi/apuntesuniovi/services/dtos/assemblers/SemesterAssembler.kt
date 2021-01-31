package es.uniovi.apuntesuniovi.services.dtos.assemblers

import es.uniovi.apuntesuniovi.infrastructure.messages.SemesterMessages
import es.uniovi.apuntesuniovi.models.Semester
import es.uniovi.apuntesuniovi.repositories.CourseRepository
import es.uniovi.apuntesuniovi.services.commands.courses.FindCourseByIdService
import es.uniovi.apuntesuniovi.services.dtos.entities.SemesterDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Define the entity and dto conversion methods of courses
 */
@Service
class SemesterAssembler @Autowired constructor(
  private val courseRepository: CourseRepository
) : AbstractAssembler<Semester, SemesterDto>() {
  override fun entityToDto(entity: Semester?): SemesterDto {
    logService.info("entityToDto(entity: Course) - start")
    entity?.let {
      val dto = SemesterDto(
        id = it.id,
        position = it.position,
        courseId = it.course?.id
      )
      logService.info("entityToDto(entity: Course) - end")
      return dto
    }
    logService.error("entityToDto(entity: Course) - error: ${SemesterMessages.NULL}")
    throw IllegalArgumentException(SemesterMessages.NULL)
  }

  override fun dtoToEntity(dto: SemesterDto?): Semester {
    logService.info("dtoToEntity(dto: CourseDto) - start")
    dto?.let {
      val entity = Semester()
      entity.id = it.id
      entity.position = it.position
      it.courseId?.let { id ->
        entity.course = FindCourseByIdService(courseRepository, id).execute()
      }
      logService.info("dtoToEntity(dto: CourseDto) - end")
      return entity
    }
    logService.info("dtoToEntity(dto: CourseDto) - error: ${SemesterMessages.NULL}")
    throw IllegalArgumentException(SemesterMessages.NULL)
  }
}