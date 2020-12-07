package es.uniovi.apuntesuniovi.services

import es.uniovi.apuntesuniovi.repositories.AddressRepository
import es.uniovi.apuntesuniovi.repositories.UserRepository
import es.uniovi.apuntesuniovi.services.commands.users.CreateUserService
import es.uniovi.apuntesuniovi.services.commands.users.FindAllUsersService
import es.uniovi.apuntesuniovi.services.commands.users.FindUserByUsernameService
import es.uniovi.apuntesuniovi.services.dtos.assemblers.UserAssembler
import es.uniovi.apuntesuniovi.services.dtos.entities.UserDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Service to manage users
 */
@Service
class UserService @Autowired constructor(
    private val userRepository: UserRepository,
    private val addressRepository: AddressRepository,
    private val userDtoAssembler: UserAssembler
) : AbstractService<UserDto>() {

    override fun create(dto: UserDto): List<UserDto> {
        logService.info("create(dto: UserDto) - start")
        val user = userDtoAssembler.dtoToEntity(dto)
        val result = CreateUserService(userRepository, addressRepository, user).execute()
        logService.info("create(dto: UserDto) - end")
        return userDtoAssembler.listToDto(result)
    }

    override fun findAll(): List<UserDto> {
        logService.info("findAll() - start")
        val result = FindAllUsersService(userRepository).execute()
        logService.info("findAll() - end")
        return userDtoAssembler.listToDto(result)
    }

    /**
     * Returns the user whose username matches
     *
     * @param username User identifier
     */
    fun findByUsername(username: String): UserDto {
        logService.info("findByUsername(username: ${username}) - start")
        val result = FindUserByUsernameService(userRepository, username).execute()
        logService.info("findByUsername(username: ${username}) - end")
        return userDtoAssembler.entityToDto(result)
    }
}