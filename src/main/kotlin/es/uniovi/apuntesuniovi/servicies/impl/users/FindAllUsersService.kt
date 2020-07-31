package es.uniovi.apuntesuniovi.servicies.impl.users

import es.uniovi.apuntesuniovi.infrastructure.Command
import es.uniovi.apuntesuniovi.log.LogService
import es.uniovi.apuntesuniovi.repositories.UserRepository
import es.uniovi.apuntesuniovi.servicies.dtos.entities.UserDto
import es.uniovi.apuntesuniovi.servicies.dtos.impl.UserDtoAssembler

class FindAllUsersService(
        private val userRepository: UserRepository,
        private val userDtoAssembler: UserDtoAssembler
) : Command<List<UserDto>> {
    private val logService = LogService(this.javaClass)

    override fun execute(): List<UserDto> {
        logService.info("execute() - start")
        val list = userRepository.findAll()
        val result = userDtoAssembler.listToDto(list)
        logService.info("execute() - end")
        return result
    }
}