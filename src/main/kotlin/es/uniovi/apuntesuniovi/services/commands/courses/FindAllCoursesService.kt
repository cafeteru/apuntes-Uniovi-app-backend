package es.uniovi.apuntesuniovi.services.commands.courses

import es.uniovi.apuntesuniovi.infrastructure.AbstractCommand
import es.uniovi.apuntesuniovi.models.Course
import es.uniovi.apuntesuniovi.repositories.CourseRepository

/**
 * Return all courses in service layer
 */
class FindAllCoursesService(
    private val courseRepository: CourseRepository
) : AbstractCommand<List<Course>>() {
    override fun execute(): List<Course> {
        logService.info("execute() - start")
        val list = courseRepository.findAll()
        logService.info("execute() - end")
        return list
    }
}