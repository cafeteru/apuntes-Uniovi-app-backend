package es.uniovi.apuntesuniovi.servicies.impl.users

import es.uniovi.apuntesuniovi.infrastructure.Command
import es.uniovi.apuntesuniovi.infrastructure.constants.ExceptionMessages.ALREADY_REGISTERED_USERNAME
import es.uniovi.apuntesuniovi.log.LogService
import es.uniovi.apuntesuniovi.repositories.UserRepository
import es.uniovi.apuntesuniovi.servicies.dtos.entities.UserDto
import es.uniovi.apuntesuniovi.servicies.dtos.impl.UserDtoAssembler
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

/**
 * Service class used to add a user
 */
class SaveUserService(
        private val userRepository: UserRepository,
        private val userDtoAssembler: UserDtoAssembler,
        private val bCryptPasswordEncoder: BCryptPasswordEncoder,
        private val userDto: UserDto
) : Command<List<UserDto>> {
    private val logService = LogService(this.javaClass)

    override fun execute(): List<UserDto> {
        logService.info("execute() - start")
        val list = ArrayList<UserDto>()
        checkUser()
        userDto.password = bCryptPasswordEncoder.encode(userDto.password)
        val person = userDtoAssembler.dtoToEntity(userDto)
        val result = userRepository.save(person)
        list.add(userDtoAssembler.entityToDto(result))
        logService.info("execute() - end")
        return list
    }

    private fun checkUser() {
        logService.info("checkUser() - start")
        val optional = userRepository.findByUsername(userDto.username)
        if (optional.isPresent) {
            logService.error("checkUser() - $ALREADY_REGISTERED_USERNAME")
            throw IllegalArgumentException(ALREADY_REGISTERED_USERNAME)
        }
        logService.info("checkUser() - end")
    }
}