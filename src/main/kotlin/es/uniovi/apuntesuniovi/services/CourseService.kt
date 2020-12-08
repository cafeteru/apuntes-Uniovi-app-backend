package es.uniovi.apuntesuniovi.services

import es.uniovi.apuntesuniovi.models.Course
import es.uniovi.apuntesuniovi.repositories.CourseRepository
import es.uniovi.apuntesuniovi.services.commands.courses.CreateCourseService
import es.uniovi.apuntesuniovi.services.commands.courses.FindAllCoursesService
import es.uniovi.apuntesuniovi.services.dtos.assemblers.CourseAssembler
import es.uniovi.apuntesuniovi.services.dtos.entities.CourseDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Service

/**
 * Service to manage courses
 */
@Service
class CourseService @Autowired constructor(
    private val courseRepository: CourseRepository,
    courseAssembler: CourseAssembler
) : BaseService<Course, CourseDto>(courseRepository, courseAssembler) {

    override fun create(repository: JpaRepository<Course, Long>, entity: Course): List<Course> {
        return CreateCourseService(courseRepository, entity).execute()
    }

    override fun findAll(repository: JpaRepository<Course, Long>): List<Course> {
        return FindAllCoursesService(courseRepository).execute()
    }
}