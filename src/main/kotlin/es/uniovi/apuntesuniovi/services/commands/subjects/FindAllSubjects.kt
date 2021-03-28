package es.uniovi.apuntesuniovi.services.commands.subjects

import es.uniovi.apuntesuniovi.dtos.entities.SubjectDto
import es.uniovi.apuntesuniovi.infrastructure.AbstractCommand
import es.uniovi.apuntesuniovi.models.Subject
import es.uniovi.apuntesuniovi.repositories.SubjectRepository
import es.uniovi.apuntesuniovi.repositories.builders.SubjectBuilder
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

/**
 * Return all subjects in service layer
 */
class FindAllSubjects(
  private val subjectRepository: SubjectRepository,
  private val subjectDto: SubjectDto?,
  private val pageable: Pageable
) : AbstractCommand<Page<Subject>>() {

  override fun execute(): Page<Subject> {
    logService.info("execute() - start")
    val filters = SubjectBuilder().createBuilder(subjectDto)
    val list = subjectRepository.findAll(filters, pageable)
    logService.info("execute() - end")
    return list
  }
}