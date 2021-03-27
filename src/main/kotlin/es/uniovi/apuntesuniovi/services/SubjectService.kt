package es.uniovi.apuntesuniovi.services

import es.uniovi.apuntesuniovi.models.Subject
import es.uniovi.apuntesuniovi.repositories.SubjectRepository
import es.uniovi.apuntesuniovi.services.commands.subjects.CreateSubject
import es.uniovi.apuntesuniovi.services.commands.subjects.FindAllSubjects
import es.uniovi.apuntesuniovi.dtos.assemblers.SubjectAssembler
import es.uniovi.apuntesuniovi.dtos.entities.SubjectDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Service

/**
 * Service to manage subjects
 */
@Service
class SubjectService @Autowired constructor(
  private val subjectRepository: SubjectRepository,
  subjectAssembler: SubjectAssembler,
) : BaseService<Subject, SubjectDto>(subjectRepository, subjectAssembler) {

  override fun create(
    repository: PagingAndSortingRepository<Subject, Long>,
    entity: Subject
  ): Subject {
    return CreateSubject(subjectRepository, entity).execute()
  }

  override fun findAll(
    repository: PagingAndSortingRepository<Subject, Long>,
    pageable: Pageable
  ): Page<Subject> {
    return FindAllSubjects(subjectRepository, pageable).execute()
  }
}