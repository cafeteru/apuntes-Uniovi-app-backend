package es.uniovi.apuntesuniovi.services.commands.teachSubjects

import es.uniovi.apuntesuniovi.models.TeachSubject
import es.uniovi.apuntesuniovi.repositories.TeachSubjectRepository
import es.uniovi.apuntesuniovi.services.commands.BaseFindByIdService

/**
 * Return teachSubject by id in service layer
 */
class FindTeachSubjectByIdService(
    teachSubjectRepository: TeachSubjectRepository,
    id: Long
) : BaseFindByIdService<TeachSubject>(teachSubjectRepository, id)