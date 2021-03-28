package es.uniovi.apuntesuniovi.services

import es.uniovi.apuntesuniovi.dtos.assemblers.SubjectAssembler
import es.uniovi.apuntesuniovi.dtos.entities.SubjectDto
import es.uniovi.apuntesuniovi.infrastructure.log.LogService
import es.uniovi.apuntesuniovi.repositories.SubjectRepository
import es.uniovi.apuntesuniovi.services.commands.subjects.CreateSubject
import es.uniovi.apuntesuniovi.services.commands.subjects.FindAllSubjects
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

/**
 * Service to manage subjects
 */
@Service
class SubjectService @Autowired constructor(
  private val subjectRepository: SubjectRepository,
  private val subjectAssembler: SubjectAssembler,
) {
  private val logService = LogService(this.javaClass)

  /**
   * Create a new subject
   *
   * @param dto Subject to create
   */
  fun create(dto: SubjectDto): SubjectDto {
    logService.info("create(dto: UserDto) - start")
    val subject = subjectAssembler.dtoToEntity(dto)
    val result = CreateSubject(subjectRepository, subject).execute()
    logService.info("create(dto: UserDto) - end")
    return subjectAssembler.entityToDto(result)
  }

  /**
   * Returns all subjects
   *
   * @param pageable Pageable
   */
  fun findAll(pageable: Pageable, subjectDto: SubjectDto?): Page<SubjectDto> {
    logService.info("findAll() - start")
    val result = FindAllSubjects(subjectRepository, subjectDto, pageable).execute()
    logService.info("findAll() - end")
    return result.map { entity -> subjectAssembler.entityToDto(entity) }
  }
}