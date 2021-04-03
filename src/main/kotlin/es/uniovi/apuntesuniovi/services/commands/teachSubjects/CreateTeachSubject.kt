package es.uniovi.apuntesuniovi.services.commands.teachSubjects

import es.uniovi.apuntesuniovi.models.TeachSubject
import es.uniovi.apuntesuniovi.repositories.TeachSubjectRepository
import es.uniovi.apuntesuniovi.services.commands.BaseCreateAll

/**
 * Create a TeachSubject in service layer
 */
class CreateTeachSubject(
    private val teachSubjectRepository: TeachSubjectRepository,
    private val teachSubjects: List<TeachSubject>
) : BaseCreateAll<TeachSubject>(teachSubjectRepository, teachSubjects) {
}