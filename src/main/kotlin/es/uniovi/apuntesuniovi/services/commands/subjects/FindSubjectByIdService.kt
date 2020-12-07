package es.uniovi.apuntesuniovi.services.commands.subjects

import es.uniovi.apuntesuniovi.models.Subject
import es.uniovi.apuntesuniovi.repositories.SubjectRepository
import es.uniovi.apuntesuniovi.services.commands.BaseFindByIdService

/**
 * Return subject by id in service layer
 */
class FindSubjectByIdService(
    subjectRepository: SubjectRepository,
    id: Long
) : BaseFindByIdService<Subject>(subjectRepository, id)