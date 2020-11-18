package es.uniovi.apuntesuniovi.servicies.impl.users

import es.uniovi.apuntesuniovi.entities.User
import es.uniovi.apuntesuniovi.infrastructure.Command
import es.uniovi.apuntesuniovi.infrastructure.constants.ExceptionMessages
import es.uniovi.apuntesuniovi.log.LogService
import es.uniovi.apuntesuniovi.repositories.UserRepository
import es.uniovi.apuntesuniovi.servicies.dtos.entities.UserDto
import es.uniovi.apuntesuniovi.servicies.dtos.impl.UserDtoAssembler
import java.util.*

class FindUserByUsernameService(
        private val userRepository: UserRepository,
        private val userDtoAssembler: UserDtoAssembler,
        private val username: String?
) : Command<UserDto> {
    private val logService = LogService(this.javaClass)

    override fun execute(): UserDto {
        logService.info("execute() - start")
        if (username.isNullOrBlank()) {
            logService.error("execute() - error")
            throw IllegalArgumentException(ExceptionMessages.INVALID_USERNAME)
        }
        val optional: Optional<User> = userRepository.findByUsername(username)
        if (optional.isPresent) {
            logService.info("execute() - end")
            return userDtoAssembler.entityToDto(optional.get())
        }
        logService.error("execute() - error")
        throw IllegalArgumentException(ExceptionMessages.NOT_FOUND_USERNAME)
    }
}