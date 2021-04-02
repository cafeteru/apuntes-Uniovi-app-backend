package es.uniovi.apuntesuniovi.services.commands.users

import es.uniovi.apuntesuniovi.infrastructure.messages.UserMessages
import es.uniovi.apuntesuniovi.models.User
import es.uniovi.apuntesuniovi.repositories.UserRepository
import es.uniovi.apuntesuniovi.services.commands.BaseDelete

/**
 * Delete a user
 */
class DeleteUser(
    userRepository: UserRepository,
    id: Long,
) : BaseDelete<User>(userRepository, id) {

    override fun getMessageInvalidId(): String {
        return UserMessages.INVALID_ID
    }

    override fun getMessageNotFound(): String {
        return UserMessages.NOT_FOUND
    }

}