package es.uniovi.apuntesuniovi.services.commands.teachSubjectRegistry

import es.uniovi.apuntesuniovi.models.TeachSubject
import es.uniovi.apuntesuniovi.models.TeachSubjectRegistry
import es.uniovi.apuntesuniovi.infrastructure.AbstractCommand
import es.uniovi.apuntesuniovi.repositories.TeachSubjectRegistryRepository
import java.time.LocalDate

/**
 * Create a teaching registry for a subject in service layer
 */
class CreateTeachSubjectRegistryService(
    private val teachSubjectRegistryRepository: TeachSubjectRegistryRepository,
    private val teachSubject: TeachSubject,
    private val date: LocalDate
) : AbstractCommand<List<TeachSubjectRegistry>>() {
    override fun execute(): List<TeachSubjectRegistry> {
        logService.info("execute() - start")
        var teachSubjectRegistry = TeachSubjectRegistry()
        teachSubjectRegistry.teachSubject = teachSubject
        teachSubjectRegistry.initDay = date
        teachSubjectRegistry = teachSubjectRegistryRepository.save(teachSubjectRegistry)
        logService.info("execute() - end")
        return listOf(teachSubjectRegistry)
    }
}