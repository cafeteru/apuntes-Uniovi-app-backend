package es.uniovi.apuntesuniovi.servicies.commands.users

import es.uniovi.apuntesuniovi.entities.User
import es.uniovi.apuntesuniovi.infrastructure.AbstractCommand
import es.uniovi.apuntesuniovi.infrastructure.messages.UserMessages
import es.uniovi.apuntesuniovi.repositories.UserRepository
import java.util.*

/**
 * Find user by username in service layer
 */
class FindUserByUsernameService(
    private val userRepository: UserRepository,
    private val username: String?
) : AbstractCommand<User>() {
    override fun execute(): User {
        logService.info("execute() - start")
        if (username.isNullOrBlank()) {
            logService.error("execute() - error")
            throw IllegalArgumentException(UserMessages.INVALID_USERNAME)
        }
        val optional: Optional<User> = userRepository.findByUsername(username)
        if (optional.isPresent) {
            logService.info("execute() - end")
            return optional.get()
        }
        logService.error("execute() - error")
        throw IllegalArgumentException(UserMessages.NOT_FOUND_USERNAME)
    }
}