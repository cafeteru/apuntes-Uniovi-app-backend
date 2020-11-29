package es.uniovi.apuntesuniovi.servicies.impl

import es.uniovi.apuntesuniovi.infrastructure.log.LogService
import es.uniovi.apuntesuniovi.repositories.RepositoryFactory
import es.uniovi.apuntesuniovi.servicies.UserService
import es.uniovi.apuntesuniovi.servicies.dtos.DtoFactory
import es.uniovi.apuntesuniovi.servicies.dtos.entities.UserDto
import es.uniovi.apuntesuniovi.servicies.impl.users.FindAllUsersService
import es.uniovi.apuntesuniovi.servicies.impl.users.FindUserByUsernameService
import es.uniovi.apuntesuniovi.servicies.impl.users.SaveUserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserServiceImpl @Autowired constructor(
        private val repositoryFactory: RepositoryFactory,
        private val dtoFactory: DtoFactory
) : UserService {
    private val logService = LogService(this.javaClass)

    override fun findAll(): List<UserDto> {
        logService.info("findAll() - start")
        val result = FindAllUsersService(repositoryFactory.getUsers(), dtoFactory.getUsers()).execute()
        logService.info("findAll() - end")
        return result
    }

    override fun findByUsername(username: String): UserDto {
        logService.info("findByUsername(username: ${username}) - start")
        val result = FindUserByUsernameService(repositoryFactory.getUsers(), dtoFactory.getUsers(), username).execute()
        logService.info("findByUsername(username: ${username}) - end")
        return result
    }

    override fun create(userDto: UserDto): List<UserDto> {
        logService.info("create(userDto: UserDto) - start")
        val result = SaveUserService(
                repositoryFactory.getUsers(), dtoFactory.getUsers(), userDto).execute()
        logService.info("create(userDto: UserDto) - end")
        return result
    }
}