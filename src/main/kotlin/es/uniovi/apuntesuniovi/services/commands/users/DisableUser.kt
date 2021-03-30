package es.uniovi.apuntesuniovi.services.commands.users

import es.uniovi.apuntesuniovi.infrastructure.AbstractCommand
import es.uniovi.apuntesuniovi.infrastructure.messages.UserMessages
import es.uniovi.apuntesuniovi.models.User
import es.uniovi.apuntesuniovi.repositories.UserRepository
import es.uniovi.apuntesuniovi.validators.impl.ValidatorId

/**
 * Change the value active of a user
 */
class DisableUser(
    private val userRepository: UserRepository,
    private val id: Long,
    private val active: Boolean
) : AbstractCommand<User>() {

    override fun execute(): User {
        if (ValidatorId(id).isValid()) {
            val optional = userRepository.findById(id)
            if (optional.isPresent) {
                val user = optional.get()
                user.active = active
                return userRepository.save(user)
            }
            throw IllegalArgumentException(UserMessages.NOT_FOUND)
        }
        throw IllegalArgumentException(UserMessages.INVALID_ID)
    }

}