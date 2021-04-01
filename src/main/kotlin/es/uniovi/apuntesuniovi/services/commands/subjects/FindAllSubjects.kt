package es.uniovi.apuntesuniovi.services.commands.subjects

import es.uniovi.apuntesuniovi.dtos.entities.SubjectDto
import es.uniovi.apuntesuniovi.dtos.entities.UserDto
import es.uniovi.apuntesuniovi.infrastructure.AbstractCommand
import es.uniovi.apuntesuniovi.models.Subject
import es.uniovi.apuntesuniovi.models.User
import es.uniovi.apuntesuniovi.repositories.SubjectRepository
import es.uniovi.apuntesuniovi.repositories.builders.SubjectBuilder
import es.uniovi.apuntesuniovi.repositories.builders.UserBuilder
import es.uniovi.apuntesuniovi.services.commands.BaseFindAllService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

/**
 * Return all subjects in service layer
 */
class FindAllSubjects(
     subjectRepository: SubjectRepository,
     subjectDto: SubjectDto?,
     pageable: Pageable
) : BaseFindAllService<Subject, SubjectDto>(
    subjectRepository, SubjectBuilder(), subjectDto, pageable
)