package es.uniovi.apuntesuniovi.services

import es.uniovi.apuntesuniovi.models.TeachSubject
import es.uniovi.apuntesuniovi.repositories.TeachSubjectRepository
import es.uniovi.apuntesuniovi.services.commands.teachSubjects.CreateTeachSubject
import es.uniovi.apuntesuniovi.services.commands.teachSubjects.FindAllTeachSubjects
import es.uniovi.apuntesuniovi.services.dtos.assemblers.TeachSubjectAssembler
import es.uniovi.apuntesuniovi.services.dtos.entities.TeachSubjectDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Service

/**
 * Service to manage careers
 */
@Service
class TeachSubjectService @Autowired constructor(
  private val teachSubjectRepository: TeachSubjectRepository,
  teachSubjectAssembler: TeachSubjectAssembler
) : BaseService<TeachSubject, TeachSubjectDto>(teachSubjectRepository, teachSubjectAssembler) {

  override fun create(
    repository: PagingAndSortingRepository<TeachSubject, Long>,
    entity: TeachSubject
  ): TeachSubject {
    return CreateTeachSubject(teachSubjectRepository, entity).execute()
  }

  override fun findAll(
    repository: PagingAndSortingRepository<TeachSubject, Long>,
    pageable: Pageable
  ): Page<TeachSubject> {
    return FindAllTeachSubjects(teachSubjectRepository, pageable).execute()
  }
}