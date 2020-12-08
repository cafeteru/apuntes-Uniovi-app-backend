package es.uniovi.apuntesuniovi.controllers.commands.subjects

import es.uniovi.apuntesuniovi.controllers.commands.BaseFindAll
import es.uniovi.apuntesuniovi.models.Subject
import es.uniovi.apuntesuniovi.services.SubjectService
import es.uniovi.apuntesuniovi.services.dtos.entities.SubjectDto

/**
 * Return all subjects in controller layer
 */
class FindAllSubjects(subjectService: SubjectService) : BaseFindAll<Subject, SubjectDto>(subjectService)