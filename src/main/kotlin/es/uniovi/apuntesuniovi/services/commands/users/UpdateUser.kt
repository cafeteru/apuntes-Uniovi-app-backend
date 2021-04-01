package es.uniovi.apuntesuniovi.services.commands.users

import es.uniovi.apuntesuniovi.infrastructure.AbstractCommand
import es.uniovi.apuntesuniovi.infrastructure.messages.UserMessages
import es.uniovi.apuntesuniovi.models.User
import es.uniovi.apuntesuniovi.repositories.AddressRepository
import es.uniovi.apuntesuniovi.repositories.UserRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

/**
 * Update a user in service layer
 */
class UpdateUser(
    private val userRepository: UserRepository,
    private val addressRepository: AddressRepository,
    private val id: Long,
    private val user: User
) : AbstractCommand<User>() {

    override fun execute(): User {
        logService.info("execute() - start")
        val optional = userRepository.findById(id)
        if (optional.isPresent) {
            checkUniqueUsername()
            checkUniqueNumberIdentification()
            if (user.password != null) {
                user.password = BCryptPasswordEncoder().encode(user.password)
            } else {
                user.password = optional.get().password
            }
            user.id = id
            user.address?.let {
                user.address = addressRepository.save(it)
            }
            val result = userRepository.save(user)
            logService.info("execute() - end")
            return result
        }
        throw IllegalArgumentException(UserMessages.NOT_FOUND)
    }

    private fun checkUniqueUsername() {
        logService.info("checkUniqueUsername() - start")
        val username = user.username
        if (username != null) {
            val optional = userRepository.findByUsername(username)
            if (optional.isPresent && optional.get().id != id) {
                throw IllegalArgumentException(UserMessages.ALREADY_REGISTERED_USERNAME)
            }
            logService.info("checkUniqueUsername() - end")
        } else {
            throw IllegalArgumentException(UserMessages.INVALID_DATA_USER)
        }
    }

    private fun checkUniqueNumberIdentification() {
        logService.info("checkUniqueNumberIdentification() - start")
        val numberIdentification = user.numberIdentification
        if (numberIdentification != null) {
            val optional = userRepository.findByNumberIdentification(numberIdentification)
            if (optional.isPresent && optional.get().id != id) {
                throw IllegalArgumentException(UserMessages.ALREADY_REGISTERED_NUMBER_IDENTIFICATION)
            }
            logService.info("checkUniqueNumberIdentification() - end")
        }
    }
}