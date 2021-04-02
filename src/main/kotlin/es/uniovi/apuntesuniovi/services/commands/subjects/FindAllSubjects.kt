package es.uniovi.apuntesuniovi.services.commands.subjects

import es.uniovi.apuntesuniovi.dtos.entities.SubjectDto
import es.uniovi.apuntesuniovi.models.Subject
import es.uniovi.apuntesuniovi.repositories.SubjectRepository
import es.uniovi.apuntesuniovi.repositories.builders.SubjectBuilder
import es.uniovi.apuntesuniovi.services.commands.BaseFindAll
import org.springframework.data.domain.Pageable

/**
 * Return all subjects in service layer
 */
class FindAllSubjects(
     subjectRepository: SubjectRepository,
     subjectDto: SubjectDto?,
     pageable: Pageable
) : BaseFindAll<Subject, SubjectDto>(
    subjectRepository, SubjectBuilder(), subjectDto, pageable
)