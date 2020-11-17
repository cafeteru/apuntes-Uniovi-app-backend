package es.uniovi.apuntesuniovi.servicies.impl.users

import es.uniovi.apuntesuniovi.infrastructure.Command
import es.uniovi.apuntesuniovi.infrastructure.constants.ExceptionMessages.ALREADY_REGISTERED_USERNAME
import es.uniovi.apuntesuniovi.log.LogService
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
        private val userDtoAssembler: UserDtoAssembler,
        private val userDto: UserDto?
) : Command<List<UserDto>> {
    private val logService = LogService(this.javaClass)
    private val bCryptPasswordEncoder = BCryptPasswordEncoder()

    override fun execute(): List<UserDto> {
        logService.info("execute() - start")
        checkUniqueUsername()
        val list = ArrayList<UserDto>()
        userDto?.password = bCryptPasswordEncoder.encode(userDto?.password)
        val person = userDtoAssembler.dtoToEntity(userDto)
        val result = userRepository.save(person)
        list.add(userDtoAssembler.entityToDto(result))
        logService.info("execute() - end")
        return list
    }

    private fun checkUniqueUsername() {
        logService.info("check() - start")
        val optional = userDto?.username?.let { userRepository.findByUsername(it) }
        if (optional != null && optional.isPresent) {
            logService.error("check() - error")
            throw IllegalArgumentException(ALREADY_REGISTERED_USERNAME)
        }
        logService.info("check() - end")
    }
}