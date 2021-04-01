package es.uniovi.apuntesuniovi.services.commands.users

import es.uniovi.apuntesuniovi.infrastructure.messages.UserMessages
import es.uniovi.apuntesuniovi.models.User
import es.uniovi.apuntesuniovi.repositories.AddressRepository
import es.uniovi.apuntesuniovi.repositories.UserRepository
import es.uniovi.apuntesuniovi.services.commands.BaseUpdateCommand
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

/**
 * Update a user in service layer
 */
class UpdateUser(
    private val userRepository: UserRepository,
    private val addressRepository: AddressRepository,
    private val id: Long,
    private val user: User
) : BaseUpdateCommand<User>(userRepository, id, user) {

    override fun checkData() {
        logService.info("checkData() - start")
        checkUniqueUsername()
        checkUniqueNumberIdentification()
        if (user.password != null) {
            user.password = BCryptPasswordEncoder().encode(user.password)
        } else {
            user.password = original?.password
        }
        user.id = id
        user.address?.let {
            user.address = addressRepository.save(it)
        }
        logService.info("checkData() - end")
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

    override fun getMessageNotFound(): String {
        return UserMessages.NOT_FOUND
    }
}