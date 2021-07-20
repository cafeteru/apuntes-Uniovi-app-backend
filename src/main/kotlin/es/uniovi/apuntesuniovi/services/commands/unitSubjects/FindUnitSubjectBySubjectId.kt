package es.uniovi.apuntesuniovi.services.commands.unitSubjects

import es.uniovi.apuntesuniovi.infrastructure.AbstractCommand
import es.uniovi.apuntesuniovi.models.UnitSubject
import es.uniovi.apuntesuniovi.repositories.UnitSubjectRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

class FindUnitSubjectBySubjectId(
    private val unitSubjectRepository: UnitSubjectRepository,
    private val id: Long,
    private val pageable: Pageable
) : AbstractCommand<Page<UnitSubject>>() {

    override fun execute(): Page<UnitSubject> {
        logService.info("execute() - start")
        val page = unitSubjectRepository.findBySubjectId(id, pageable)
        logService.info("execute() - start")
        return page
    }
}