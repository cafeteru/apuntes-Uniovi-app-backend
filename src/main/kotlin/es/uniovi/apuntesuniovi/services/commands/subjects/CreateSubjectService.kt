package es.uniovi.apuntesuniovi.services.commands.subjects

import es.uniovi.apuntesuniovi.models.Subject
import es.uniovi.apuntesuniovi.repositories.SubjectRepository
import es.uniovi.apuntesuniovi.services.commands.BaseCreateService

/**
 * Create a subject in service layer
 */
class CreateSubjectService(subjectRepository: SubjectRepository, subject: Subject) :
    BaseCreateService<Subject>(subjectRepository, subject)