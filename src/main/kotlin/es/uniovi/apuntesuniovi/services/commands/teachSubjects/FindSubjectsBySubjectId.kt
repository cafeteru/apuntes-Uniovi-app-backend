package es.uniovi.apuntesuniovi.services.commands.teachSubjects

import es.uniovi.apuntesuniovi.infrastructure.AbstractCommand
import es.uniovi.apuntesuniovi.models.Subject
import es.uniovi.apuntesuniovi.repositories.TeachSubjectRepository

class FindSubjectsBySubjectId(
    private val teachSubjectRepository: TeachSubjectRepository,
    private val id: Long
) : AbstractCommand<Subject>() {

    override fun execute(): Subject {
        TODO("Not yet implemented")
    }
}