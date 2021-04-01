package es.uniovi.apuntesuniovi.services.commands.teachSubjectRegistries

import es.uniovi.apuntesuniovi.infrastructure.messages.TeachSubjectRegistryMessages
import es.uniovi.apuntesuniovi.models.TeachSubjectRegistry
import es.uniovi.apuntesuniovi.repositories.TeachSubjectRegistryRepository
import es.uniovi.apuntesuniovi.services.commands.BaseFindByIdCommand

/**
 * Return teachSubjectRegistry by id in service layer
 */
class FindTeachSubjectRegistryById(
    teachSubjectRegistryRepository: TeachSubjectRegistryRepository,
    id: Long
) : BaseFindByIdCommand<TeachSubjectRegistry>(teachSubjectRegistryRepository, id) {

    override fun getMessageNotFound(): String {
        return TeachSubjectRegistryMessages.NOT_FOUND
    }

    override fun getMessageInvalidId(): String {
        return TeachSubjectRegistryMessages.INVALID_ID
    }
}