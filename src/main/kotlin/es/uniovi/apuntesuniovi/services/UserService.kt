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
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.PagingAndSortingRepository
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

    override fun create(
        repository: PagingAndSortingRepository<User, Long>,
        entity: User
    ): User {
        return CreateUserService(userRepository, addressRepository, entity).execute()
    }

    override fun findAll(
        repository: PagingAndSortingRepository<User, Long>,
        pageable: Pageable
    ): Page<User> {
        return FindAllUsersService(userRepository, pageable).execute().map {
            it.img = null
            it.password = null
            return@map it
        }
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