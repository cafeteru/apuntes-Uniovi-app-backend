package es.uniovi.apuntesuniovi.services.commands.subjects

import es.uniovi.apuntesuniovi.infrastructure.AbstractCommand
import es.uniovi.apuntesuniovi.repositories.SubjectRepository
import es.uniovi.apuntesuniovi.statistics.SubjectStatistics

class GetSubjectStatistics(
    private val SubjectRepository: SubjectRepository
) : AbstractCommand<SubjectStatistics>() {

    override fun execute(): SubjectStatistics {
        logService.info("execute() - start")
        val subjectStatistics = SubjectStatistics(
            active = SubjectRepository.countByActive(true),
            inactive = SubjectRepository.countByActive(false),
        )
        logService.info("execute() - end")
        return subjectStatistics
    }
}