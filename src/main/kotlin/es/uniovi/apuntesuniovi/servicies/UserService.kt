package es.uniovi.apuntesuniovi.servicies

import es.uniovi.apuntesuniovi.infrastructure.log.LogService
import es.uniovi.apuntesuniovi.repositories.AddressRepository
import es.uniovi.apuntesuniovi.repositories.UserRepository
import es.uniovi.apuntesuniovi.servicies.dtos.entities.UserDto
import es.uniovi.apuntesuniovi.servicies.dtos.impl.UserDtoAssembler
import es.uniovi.apuntesuniovi.servicies.impl.users.FindAllUsersService
import es.uniovi.apuntesuniovi.servicies.impl.users.FindUserByUsernameService
import es.uniovi.apuntesuniovi.servicies.impl.users.SaveUserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Service to manage users
 */
@Service
class UserService @Autowired constructor(
        private val userRepository: UserRepository,
        private val addressRepository: AddressRepository,
        private val userDtoAssembler: UserDtoAssembler
) {
    private val logService = LogService(this.javaClass)

    /**
     * Returns all users
     */
    fun findAll(): List<UserDto> {
        logService.info("findAll() - start")
        val result = FindAllUsersService(userRepository, userDtoAssembler).execute()
        logService.info("findAll() - end")
        return result
    }

    /**
     * Returns the user whose username matches
     *
     * @param username User identifier
     */
    fun findByUsername(username: String): UserDto {
        logService.info("findByUsername(username: ${username}) - start")
        val result = FindUserByUsernameService(userRepository, userDtoAssembler, username).execute()
        logService.info("findByUsername(username: ${username}) - end")
        return result
    }

    /**
     * Saves the user
     *
     * @param userDto User to save
     */
    fun create(userDto: UserDto): List<UserDto> {
        logService.info("create(userDto: UserDto) - start")
        val result = SaveUserService(userRepository, addressRepository,
                userDtoAssembler, userDto).execute()
        logService.info("create(userDto: UserDto) - end")
        return result
    }
}