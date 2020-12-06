package es.uniovi.apuntesuniovi.servicies.commands.teachSubjectRegistry

import es.uniovi.apuntesuniovi.entities.TeachSubject
import es.uniovi.apuntesuniovi.entities.TeachSubjectRegistry
import es.uniovi.apuntesuniovi.infrastructure.Command
import es.uniovi.apuntesuniovi.infrastructure.log.LogService
import es.uniovi.apuntesuniovi.repositories.TeachSubjectRegistryRepository
import java.time.LocalDate

/**
 * Create a teaching registry for a subject in service layer
 */
class CreateTeachSubjectRegistryService(
    private val teachSubjectRegistryRepository: TeachSubjectRegistryRepository,
    private val teachSubject: TeachSubject,
    private val date: LocalDate
) : Command<List<TeachSubjectRegistry>> {
    private val logService = LogService(this.javaClass)

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