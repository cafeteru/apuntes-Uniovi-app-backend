package es.uniovi.apuntesuniovi.services.commands.courses

import es.uniovi.apuntesuniovi.infrastructure.AbstractCommand
import es.uniovi.apuntesuniovi.models.Course
import es.uniovi.apuntesuniovi.repositories.CourseRepository

/**
 * Create a course in service layer
 */
class CreateCourseService(
    private val courseRepository: CourseRepository,
    private val course: Course
) : AbstractCommand<List<Course>>() {
    override fun execute(): List<Course> {
        logService.info("execute() - start")
        val result = courseRepository.save(course)
        logService.info("execute() - end")
        return listOf(result)
    }
}