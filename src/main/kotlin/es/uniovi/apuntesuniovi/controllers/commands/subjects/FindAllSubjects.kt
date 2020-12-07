package es.uniovi.apuntesuniovi.controllers.commands.subjects

import es.uniovi.apuntesuniovi.controllers.commands.AbstractFindAll
import es.uniovi.apuntesuniovi.services.SubjectService
import es.uniovi.apuntesuniovi.services.dtos.entities.SubjectDto

/**
 * Return all subjects in controller layer
 */
class FindAllSubjects(subjectService: SubjectService) : AbstractFindAll<SubjectDto>(subjectService)