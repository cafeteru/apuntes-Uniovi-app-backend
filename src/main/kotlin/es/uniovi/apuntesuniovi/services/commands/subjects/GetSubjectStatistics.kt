package es.uniovi.apuntesuniovi.services.commands.subjects

import es.uniovi.apuntesuniovi.infrastructure.AbstractCommand
import es.uniovi.apuntesuniovi.repositories.SubjectRepository
import es.uniovi.apuntesuniovi.statistics.SubjectStatistics

class GetSubjectStatistics(
    private val subjectRepository: SubjectRepository
) : AbstractCommand<SubjectStatistics>() {

    override fun execute(): SubjectStatistics {
        logService.info("execute() - start")
        val subjectStatistics = SubjectStatistics(
            active = subjectRepository.countByActive(true),
            inactive = subjectRepository.countByActive(false),
        )
        logService.info("execute() - end")
        return subjectStatistics
    }
}