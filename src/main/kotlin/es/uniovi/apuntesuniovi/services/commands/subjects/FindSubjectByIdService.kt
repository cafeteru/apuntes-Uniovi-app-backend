package es.uniovi.apuntesuniovi.services.commands.subjects

import es.uniovi.apuntesuniovi.models.Subject
import es.uniovi.apuntesuniovi.repositories.SubjectRepository
import es.uniovi.apuntesuniovi.services.commands.AbstractFindById

/**
 * Return subject by id in service layer
 */
class FindSubjectByIdService(
    subjectRepository: SubjectRepository,
    id: Long
) : AbstractFindById<Subject>(subjectRepository, id)