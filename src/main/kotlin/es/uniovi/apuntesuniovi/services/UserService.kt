package es.uniovi.apuntesuniovi.services

import es.uniovi.apuntesuniovi.dtos.Converter
import es.uniovi.apuntesuniovi.dtos.entities.UserDto
import es.uniovi.apuntesuniovi.infrastructure.log.LogService
import es.uniovi.apuntesuniovi.models.User
import es.uniovi.apuntesuniovi.repositories.AddressRepository
import es.uniovi.apuntesuniovi.repositories.UserRepository
import es.uniovi.apuntesuniovi.services.commands.users.*
import es.uniovi.apuntesuniovi.statistics.UserStatistics
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

/**
 * Service to manage users
 */
@Service
class UserService @Autowired constructor(
    private val userRepository: UserRepository,
    private val addressRepository: AddressRepository
) {
    private val logService = LogService(this.javaClass)

    /**
     * Create a new user
     *
     * @param dto User to create
     */
    fun create(dto: UserDto): UserDto {
        logService.info("create(dto: UserDto) - start")
        val user = Converter.convert(dto, User::class.java)
        val result = CreateUser(userRepository, addressRepository, user).execute()
        logService.info("create(dto: UserDto) - end")
        return convertToDto(result)
    }

    /**
     * Update a new user
     *
     * @param id User´s id
     * @param dto User to update
     */
    fun update(id: Long, dto: UserDto): UserDto {
        logService.info("update(id: Long, dto: UserDto) - start")
        val user = Converter.convert(dto, User::class.java)
        val result = UpdateUser(userRepository, addressRepository, id, user).execute()
        logService.info("update(id: Long, dto: UserDto) - end")
        return convertToDto(result)
    }

    /**
     * Returns all users
     *
     * @param pageable Pageable
     */
    fun findAll(pageable: Pageable, userDto: UserDto?): Page<UserDto> {
        logService.info("findAll() - start")
        val result = FindAllUsers(userRepository, userDto, pageable).execute()
        logService.info("findAll() - end")
        return result.map { entity -> convertToDto(entity) }
    }

    /**
     * Returns the user whose id matches
     *
     * @param id User id
     */
    fun findById(id: Long): UserDto {
        logService.info("findById() - start")
        val user = FindUserById(userRepository, id).execute()
        user.password = null
        logService.info("findById() - end")
        return Converter.convert(user, UserDto::class.java)
    }

    /**
     * Returns the user whose username matches
     *
     * @param username User identifier
     */
    fun findByUsername(username: String): UserDto {
        logService.info("findByUsername(username: ${username}) - start")
        val user = FindUserByUsername(userRepository, username).execute()
        logService.info("findByUsername(username: ${username}) - end")
        return convertToDto(user)
    }

    /**
     * Change a user's language
     *
     * @param username User's username sending the request
     * @param language Selected language
     */
    fun changeLanguage(username: String, language: String): Boolean {
        logService.info("changeLanguage(username: $username, language: $language) - start")
        val result = ChangeLanguageUser(userRepository, username, language).execute()
        logService.info("changeLanguage(username: $username, language:  $language) - end")
        return result
    }

    /**
     * Change the value active of a user
     *
     * @param id User´s id
     * @param active New value to user´s active
     */
    fun disable(id: Long, active: Boolean): UserDto {
        logService.info("disable(id: $id, active: $active) - start")
        val user = DisableUser(userRepository, id, active).execute()
        logService.info("disable(id: $id, active:  $active) - end")
        return convertToDto(user)
    }

    /**
     * Delete a user
     *
     * @param id User´s id
     */
    fun delete(id: Long): Boolean {
        logService.info("delete(id: $id) - start")
        val result = DeleteUser(userRepository, id).execute()
        logService.info("delete(id: $id) - end")
        return result
    }

    /**
     * Return user statistics
     */
    fun statistics(): UserStatistics {
        logService.info("statistics() - start")
        val result = GetUserStatistics(userRepository).execute()
        logService.info("statistics() - end")
        return result
    }

    private fun convertToDto(user: User): UserDto {
        user.img = null
        user.password = null
        return Converter.convert(user, UserDto::class.java)
    }
}