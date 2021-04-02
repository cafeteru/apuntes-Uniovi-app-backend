package es.uniovi.apuntesuniovi.services.commands.subjects

import es.uniovi.apuntesuniovi.infrastructure.messages.SubjectMessages
import es.uniovi.apuntesuniovi.models.Subject
import es.uniovi.apuntesuniovi.repositories.SubjectRepository
import es.uniovi.apuntesuniovi.services.commands.BaseUpdate

class UpdateSubject(
    subjectRepository: SubjectRepository,
    id: Long,
    subject: Subject
) : BaseUpdate<Subject>(subjectRepository, id, subject) {

    override fun checkData() {
        logService.info("checkData() - start")
        logService.info("checkData() - end")
    }

    override fun getMessageNotFound(): String {
        return SubjectMessages.NOT_FOUND
    }
}