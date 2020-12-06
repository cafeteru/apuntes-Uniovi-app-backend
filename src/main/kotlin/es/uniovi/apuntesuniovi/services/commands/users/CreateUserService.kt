package es.uniovi.apuntesuniovi.services.commands.users

import es.uniovi.apuntesuniovi.models.Address
import es.uniovi.apuntesuniovi.models.User
import es.uniovi.apuntesuniovi.infrastructure.AbstractCommand
import es.uniovi.apuntesuniovi.infrastructure.messages.UserMessages
import es.uniovi.apuntesuniovi.repositories.AddressRepository
import es.uniovi.apuntesuniovi.repositories.UserRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

/**
 * Create a user in service layer
 */
class CreateUserService(
    private val userRepository: UserRepository,
    private val addressRepository: AddressRepository,
    private val user: User
) : AbstractCommand<List<User>>() {
    override fun execute(): List<User> {
        logService.info("execute() - start")
        checkUniqueUsername()
        checkUniqueNumberIdentification()
        user.password = BCryptPasswordEncoder().encode(user.password)
        var address: Address? = null
        user.address?.let {
            address = addressRepository.save(it)
        }
        user.address = address
        val result = userRepository.save(user)
        logService.info("execute() - end")
        return listOf(result)
    }

    private fun checkUniqueUsername() {
        logService.info("checkUniqueUsername() - start")
        if (user.username.isNullOrEmpty() || user.password.isNullOrBlank()) {
            logService.error("checkUniqueUsername() - error")
            throw IllegalArgumentException(UserMessages.INVALID_DATA_USER)
        }
        val optional = user.username?.let { userRepository.findByUsername(it) }
        if (optional != null && optional.isPresent) {
            logService.error("checkUniqueUsername() - error")
            throw IllegalArgumentException(UserMessages.ALREADY_REGISTERED_USERNAME)
        }
        logService.info("checkUniqueUsername() - end")
    }

    private fun checkUniqueNumberIdentification() {
        logService.info("checkUniqueNumberIdentification() - start")
        val optional = user.numberIdentification?.let { userRepository.findByNumberIdentification(it) }
        if (optional != null && optional.isPresent) {
            logService.error("checkUniqueNumberIdentification() - error")
            throw IllegalArgumentException(UserMessages.ALREADY_REGISTERED_NUMBER_IDENTIFICATION)
        }
        logService.info("checkUniqueNumberIdentification() - end")
    }
}