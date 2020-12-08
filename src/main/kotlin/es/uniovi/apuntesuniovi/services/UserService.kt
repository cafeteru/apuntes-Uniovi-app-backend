package es.uniovi.apuntesuniovi.services

import es.uniovi.apuntesuniovi.models.User
import es.uniovi.apuntesuniovi.repositories.AddressRepository
import es.uniovi.apuntesuniovi.repositories.UserRepository
import es.uniovi.apuntesuniovi.services.commands.users.CreateUserService
import es.uniovi.apuntesuniovi.services.commands.users.FindAllUsersService
import es.uniovi.apuntesuniovi.services.commands.users.FindUserByUsernameService
import es.uniovi.apuntesuniovi.services.dtos.assemblers.UserAssembler
import es.uniovi.apuntesuniovi.services.dtos.entities.UserDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Service

/**
 * Service to manage users
 */
@Service
class UserService @Autowired constructor(
    private val userRepository: UserRepository,
    private val addressRepository: AddressRepository,
    private val userAssembler: UserAssembler
) : BaseService<User, UserDto>(userRepository, userAssembler) {

    override fun create(repository: JpaRepository<User, Long>, entity: User): List<User> {
        return CreateUserService(userRepository, addressRepository, entity).execute()
    }

    override fun findAll(repository: JpaRepository<User, Long>): List<User> {
        return FindAllUsersService(userRepository).execute()
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
        return userAssembler.entityToDto(result)
    }
}