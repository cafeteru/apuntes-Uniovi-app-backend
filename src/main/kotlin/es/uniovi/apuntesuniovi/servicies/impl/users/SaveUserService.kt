package es.uniovi.apuntesuniovi.servicies.impl.users

import es.uniovi.apuntesuniovi.infrastructure.Command
import es.uniovi.apuntesuniovi.log.LogService
import es.uniovi.apuntesuniovi.repositories.RepositoryFactory
import es.uniovi.apuntesuniovi.servicies.dtos.DtoFactory
import es.uniovi.apuntesuniovi.servicies.dtos.entities.UserDto
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

class SaveUserService(
        private val repositoryFactory: RepositoryFactory,
        private val dtoFactory: DtoFactory,
        private val bCryptPasswordEncoder: BCryptPasswordEncoder,
        private val userDto: UserDto
) : Command<List<UserDto>> {
    private val logService = LogService(this.javaClass)

    override fun execute(): List<UserDto> {
        logService.info("execute() - start")
        val list = ArrayList<UserDto>()
        userDto.password = bCryptPasswordEncoder.encode(userDto.password)
        val person = dtoFactory.getUsers().dtoToEntity(userDto)
        val result = repositoryFactory.getUsers().save(person)
        list.add(dtoFactory.getUsers().entityToDto(result))
        logService.info("execute() - end")
        return list
    }
}