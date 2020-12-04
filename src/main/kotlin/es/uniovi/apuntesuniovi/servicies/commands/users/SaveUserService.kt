package es.uniovi.apuntesuniovi.servicies.commands.users

import es.uniovi.apuntesuniovi.entities.Address
import es.uniovi.apuntesuniovi.infrastructure.Command
import es.uniovi.apuntesuniovi.infrastructure.exceptions.messages.UserMessages
import es.uniovi.apuntesuniovi.infrastructure.log.LogService
import es.uniovi.apuntesuniovi.repositories.AddressRepository
import es.uniovi.apuntesuniovi.repositories.UserRepository
import es.uniovi.apuntesuniovi.servicies.dtos.entities.UserDto
import es.uniovi.apuntesuniovi.servicies.dtos.impl.UserDtoAssembler
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.util.*

/**
 * Service class used to add a user
 */
class SaveUserService(
        private val userRepository: UserRepository,
        private val addressRepository: AddressRepository,
        private val userDtoAssembler: UserDtoAssembler,
        private val userDto: UserDto?
) : Command<List<UserDto>> {
    private val logService = LogService(this.javaClass)
    private val bCryptPasswordEncoder = BCryptPasswordEncoder()

    override fun execute(): List<UserDto> {
        logService.info("execute() - start")
        checkUniqueUsername()
        checkUniqueNumberIdentification()
        userDto?.password = bCryptPasswordEncoder.encode(userDto?.password)
        val user = userDtoAssembler.dtoToEntity(userDto)
        var address: Address? = null
        user.address?.let {
            address = addressRepository.save(it)
        }
        user.address = address
        val result = userRepository.save(user)
        val list = ArrayList<UserDto>()
        list.add(userDtoAssembler.entityToDto(result))
        logService.info("execute() - end")
        return list
    }

    private fun checkUniqueUsername() {
        logService.info("checkUniqueUsername() - start")
        if (userDto?.username.isNullOrEmpty() || userDto?.password.isNullOrBlank()) {
            logService.error("checkUniqueUsername() - error")
            throw IllegalArgumentException(UserMessages.INVALID_DATA_USER)
        }
        val optional = userDto?.username?.let { userRepository.findByUsername(it) }
        if (optional != null && optional.isPresent) {
            logService.error("checkUniqueUsername() - error")
            throw IllegalArgumentException(UserMessages.ALREADY_REGISTERED_USERNAME)
        }
        logService.info("checkUniqueUsername() - end")
    }

    private fun checkUniqueNumberIdentification() {
        logService.info("checkUniqueNumberIdentification() - start")
        val optional = userDto?.numberIdentification?.let { userRepository.findByNumberIdentification(it) }
        if (optional != null && optional.isPresent) {
            logService.error("checkUniqueNumberIdentification() - error")
            throw IllegalArgumentException(UserMessages.ALREADY_REGISTERED_NUMBER_IDENTIFICATION)
        }
        logService.info("checkUniqueNumberIdentification() - end")
    }
}