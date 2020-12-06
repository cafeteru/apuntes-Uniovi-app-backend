package es.uniovi.apuntesuniovi.servicies.commands.subjects

import es.uniovi.apuntesuniovi.entities.Subject
import es.uniovi.apuntesuniovi.repositories.SubjectRepository
import es.uniovi.apuntesuniovi.servicies.commands.AbstractFindById

/**
 * Return subject by id in service layer
 */
class FindSubjectByIdService(
    subjectRepository: SubjectRepository,
    id: Long
) : AbstractFindById<Subject>(subjectRepository, id)