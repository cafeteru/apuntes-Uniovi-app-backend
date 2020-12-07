package es.uniovi.apuntesuniovi.services

import es.uniovi.apuntesuniovi.repositories.CourseRepository
import es.uniovi.apuntesuniovi.services.commands.courses.CreateCourseService
import es.uniovi.apuntesuniovi.services.commands.courses.FindAllCoursesService
import es.uniovi.apuntesuniovi.services.dtos.assemblers.CourseAssembler
import es.uniovi.apuntesuniovi.services.dtos.entities.CourseDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Service to manage courses
 */
@Service
class CourseService @Autowired constructor(
    private val courseRepository: CourseRepository,
    private val courseAssembler: CourseAssembler
) : BaseService<CourseDto>() {

    override fun create(dto: CourseDto): List<CourseDto> {
        logService.info("create(dto: CareerDto) - start")
        val course = courseAssembler.dtoToEntity(dto)
        val result = CreateCourseService(courseRepository, course).execute()
        logService.info("create(dto: CareerDto) - end")
        return courseAssembler.listToDto(result)
    }

    override fun findAll(): List<CourseDto> {
        logService.info("findAll() - start")
        val result = FindAllCoursesService(courseRepository).execute()
        logService.info("findAll() - end")
        return courseAssembler.listToDto(result)
    }
}