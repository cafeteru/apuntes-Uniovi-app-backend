package es.uniovi.apuntesuniovi.services.commands.users

import es.uniovi.apuntesuniovi.infrastructure.AbstractCommand
import es.uniovi.apuntesuniovi.infrastructure.messages.UserMessages
import es.uniovi.apuntesuniovi.models.User
import es.uniovi.apuntesuniovi.repositories.UserRepository
import es.uniovi.apuntesuniovi.validators.impl.ValidatorId
import org.springframework.util.Assert

/**
 * Change the value active of a user
 */
class DisableUser(
    private val userRepository: UserRepository,
    private val id: Long,
    private val active: Boolean
) : AbstractCommand<User>() {

    override fun execute(): User {
        Assert.isTrue(ValidatorId(id).isValid(), UserMessages.INVALID_ID)
        val optional = userRepository.findById(id)
        Assert.isTrue(optional.isPresent, UserMessages.NOT_FOUND)
        val user = optional.get()
        user.active = active
        return userRepository.save(user)
    }

}