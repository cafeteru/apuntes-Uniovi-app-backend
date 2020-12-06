package es.uniovi.apuntesuniovi.servicies.commands.users

import es.uniovi.apuntesuniovi.entities.User
import es.uniovi.apuntesuniovi.infrastructure.Command
import es.uniovi.apuntesuniovi.infrastructure.exceptions.messages.UserMessages
import es.uniovi.apuntesuniovi.infrastructure.log.LogService
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
            throw IllegalArgumentException(UserMessages.INVALID_USERNAME)
        }
        val optional: Optional<User> = userRepository.findByUsername(username)
        if (optional.isPresent) {
            logService.info("execute() - end")
            return userDtoAssembler.entityToDto(optional.get())
        }
        logService.error("execute() - error")
        throw IllegalArgumentException(UserMessages.NOT_FOUND_USERNAME)
    }
}